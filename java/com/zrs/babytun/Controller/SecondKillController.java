package com.zrs.babytun.Controller;

import com.zrs.babytun.Pojo.Order;
import com.zrs.babytun.Pojo.Result;
import com.zrs.babytun.Pojo.SecondKillException;
import com.zrs.babytun.Pojo.SecondKillGoods;
import com.zrs.babytun.Service.SecondKillService;
import org.springframework.core.OrderComparator;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
public class SecondKillController {

    @Resource
    SecondKillService secondKillService;

    @RequestMapping("/seckill")
    public String goPage(){
        return "seckill";
    }

    @RequestMapping("/secondKill")
    @ResponseBody
    public Result processSecondKill(String userId, Integer ps_id) throws SecondKillException {
        Result result = new Result();
        try {
            //抢购商品
            secondKillService.processSecondKill(userId, ps_id);
            //获取订单号
            String orderNo = secondKillService.sendOrderToRabbit(userId);
            result.setCode(0);
            result.setMessage("");
            result.setData(orderNo);
        } catch (Exception e) {
            result.setCode(200);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    @RequestMapping("/checkorder")
    public String checkOrderStatus(String orderNo, Model model) {
        Order order = secondKillService.checkOrderStatus(orderNo);
        model.addAttribute("orderNo", orderNo);
        if (order == null) {
            return "waiting";
        } else {
            return "order";
        }
    }

}
