package com.zrs.babytun.scechual;

import com.zrs.babytun.Mapper.SecondKillMapper;
import com.zrs.babytun.Pojo.SecondKillGoods;
import com.zrs.babytun.Service.SecondKillService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class Schedule {
    @Resource
    SecondKillMapper secondKillMapper;
    @Resource
    RedisTemplate<String,Object> redisTemplate;

    /**
    * @description  定时检测秒杀活动开始的商品
    **/
    @Scheduled(cron="*/5 * * * * ?")
    public void startSecondKill(){
        List<SecondKillGoods> list=secondKillMapper.getUnStartSecondKillGoods();
        for(SecondKillGoods goods:list){
            System.out.println("商品号"+goods.getGoods_id()+"秒杀活动已开始");
            //删除重复的秒杀活动的ID
            redisTemplate.delete(Constant.SECOND_KILL_GOODS_LIST+goods.getPs_id());
            for(int i=0;i<goods.getPs_count();i++){
                //有几个秒杀数量，就存入redis几次
                redisTemplate.opsForList().rightPush(Constant.SECOND_KILL_GOODS_LIST+goods.getPs_id(),goods.getGoods_id());
            }
            goods.setStatus(1);
            //更新秒杀的状态为以开始
            secondKillMapper.updateSecondKillGoods(goods);
        }
    }

    /**
     * @description  定时检测秒杀活动结束的商品
     **/
    @Scheduled(cron="*/5 * * * * ?")
    public void expireSecondKill(){
        List<SecondKillGoods> list=secondKillMapper.getFinishedSecondKillGoods();
        for(SecondKillGoods goods:list){
            System.out.println("商品号"+goods.getGoods_id()+"秒杀活动已结束");
            goods.setStatus(2);
            //更新秒杀的装啊提为结束
            secondKillMapper.updateSecondKillGoods(goods);
            //从redis删除相应的秒杀key
            redisTemplate.delete(Constant.SECOND_KILL_GOODS_LIST+goods.getPs_id());
        }
    }
}
