package com.zrs.babytun.Service;

import com.rabbitmq.client.Channel;
import com.zrs.babytun.Mapper.OrderMapper;
import com.zrs.babytun.Mapper.SecondKillMapper;
import com.zrs.babytun.Pojo.Order;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

@Service
public class OrderService {

    @Resource
    RabbitTemplate rabbitTemplate;
    @Resource
    SecondKillMapper secondKillMapper;

    @Resource
    OrderMapper orderMapper;
    @RabbitHandler
    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(value = "queue_secondkill"),
                    exchange = @Exchange(value = "exchange_secondkill", type = "fanout"))
    )
    public void ordering(@Payload Map<String,Object> data, @Headers Map<String,Object> headers, Channel channel) throws IOException {
        System.out.println("=======获取到订单数据:" + data + "===========);");

        try {
            //模拟对接支付系统
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //将订单数据插入数据库
        Order order=new Order();
        order.setOrder_no((String) data.get("orderNo"));
        order.setUser_id((String) data.get("uid"));
        order.setAmout(19.9f);
        order.setOrder_status(0);
        order.setCreate_time(new Date());
        order.setRecv_address("xxxxxxxxxx");
        order.setRecv_mobile("138xxxx");
        order.setRecv_name("lsii");
       if(orderMapper.insertOrder(order)>0){
          Long tag= (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
          channel.basicAck(tag,false);
           System.out.println(data.get("orderNo") + "订单已创建");
       }
    }
}
