package com.zrs.babytun.Service;

import com.zrs.babytun.Mapper.OrderMapper;
import com.zrs.babytun.Mapper.SecondKillMapper;
import com.zrs.babytun.Pojo.Order;
import com.zrs.babytun.Pojo.Result;
import com.zrs.babytun.Pojo.SecondKillException;
import com.zrs.babytun.Pojo.SecondKillGoods;
import com.zrs.babytun.scechual.Constant;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

import javax.annotation.Resource;
import java.io.PipedOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class SecondKillService {

    @Resource
    SecondKillMapper secondKillMapper;

    @Resource
    OrderMapper orderMapper;

    @Resource
    RedisTemplate<String, Object> redisTemplate;


    @Resource
    RabbitTemplate rabbitTemplate;


    public void processSecondKill(String userId, Integer ps_id) throws SecondKillException {
        SecondKillGoods secondKillGoods = secondKillMapper.getSecondSkillById(ps_id);
        if (secondKillGoods == null) {
            throw new SecondKillException("秒杀活动不存在");
        }
        if (secondKillGoods.getStatus() == 0) {
            throw new SecondKillException("秒杀活动还未开始");
        } else if (secondKillGoods.getStatus() == 2) {
            throw new SecondKillException("秒杀活动已经结束");
        }
        Integer goodsID = (Integer) redisTemplate.opsForList().leftPop(Constant.SECOND_KILL_GOODS_LIST + ps_id);
        if (goodsID != null) {
            boolean isExists = redisTemplate.opsForSet().isMember(Constant.SECOND_KILL_USER_LIST + ps_id, userId);
            if (isExists) {
                redisTemplate.opsForList().rightPush(Constant.SECOND_KILL_GOODS_LIST + ps_id, goodsID);
                throw new SecondKillException("您已经参与次抢购活动，请勿重复抢购哦");
            } else {
                redisTemplate.opsForSet().add(Constant.SECOND_KILL_USER_LIST + ps_id, userId);
                System.out.println("抢购成功，赶快下单吧");
            }
        } else {
            throw new SecondKillException("商品已经被抢购光，请下次努力哦");
        }

    }

    public String sendOrderToRabbit(String userId) {
        String orderNo = UUID.randomUUID().toString().replace("-", "");
        Map<String, Object> map = new HashMap<>();
        map.put("uid", userId);
        map.put("orderNo", orderNo);
        rabbitTemplate.convertAndSend("exchange_secondkill", null, map);
        return orderNo;
    }

    public Order checkOrderStatus(String orderNo) {
        return orderMapper.getOrderByOrderId(orderNo);
    }

}
