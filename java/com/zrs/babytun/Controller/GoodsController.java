package com.zrs.babytun.Controller;

import com.zrs.babytun.Pojo.*;
import com.zrs.babytun.Service.GoodsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import javax.annotation.Resource;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class GoodsController {


    Logger logger = LoggerFactory.getLogger(GoodsController.class);

    @Resource
    GoodsService goodsService;

    @GetMapping("/goods/{goodsId}")
    public String getGoodsById(@PathVariable("goodsId") Long goodsId, Model model) {
        System.out.println("goodsId:====>"+goodsId);
        logger.info("gid:" + goodsId);
        Goods goods=goodsService.getGoodsById(goodsId);
        List<GoodsCover> goodsCovers=goodsService.getGoodsCoverById(goodsId);
        List<GoodsDetail> goodsDetails=goodsService.getGoodsDetailById(goodsId);
        List<GoodsParam> goodsParams=goodsService.getGoodsParamById(goodsId);
        model.addAttribute("goods",goods);
        //System.out.println("goods:====>"+goods);
        model.addAttribute("goodsCover",goodsCovers);
        //System.out.println("goodsCover:====>"+goodsCovers);
        model.addAttribute("goodsDetail",goodsDetails);
       // System.out.println("goodsDetail:====>"+goodsDetails);
        model.addAttribute("goodsParam",goodsParams);

        return "goods";
    }

    @GetMapping("/allGoods")
    @ResponseBody
    public List<Goods> getAllGoods() {
        return goodsService.getAllGoods();
    }


    @GetMapping("/static_all")
    @ResponseBody
    public String staticAllPage() throws IOException {
        List<Goods> goodsList=goodsService.getAllGoods();
        for(Goods goods:goodsList){
           Long goodsId=goods.getGoods_id();
            //1.???????????????????????????
            ClassLoaderTemplateResolver resolver=new ClassLoaderTemplateResolver();
            resolver.setPrefix("templates/");
            resolver.setSuffix(".html");
            //2.??????????????????
            TemplateEngine templateEngine=new TemplateEngine();
            //3.???????????????????????????
            templateEngine.setTemplateResolver(resolver);
            //4.???????????????????????????????????????
            String targetFile="D:/STATIC/"+goodsId+".html";
            FileWriter fileWriter = new FileWriter(targetFile);

            List<GoodsCover> goodsCovers=goodsService.getGoodsCoverById(goodsId);
            List<GoodsDetail> goodsDetails=goodsService.getGoodsDetailById(goodsId);
            List<GoodsParam> goodsParams=goodsService.getGoodsParamById(goodsId);

            //context.setVariable("goods",goods);
            //context.setVariable("goodsCover",goodsCovers);
            //context.setVariable("goodsDetail",goodsDetails);
            //context.setVariable("goodsParam",goodsParams);
            System.out.println("goods:====>"+goods);
             System.out.println("goodsDetail:====>"+goodsDetails);
            Map<String,Object> map=new HashMap<>();
            map.put("goods",goods);
            map.put("goodsCover",goodsCovers);
            map.put("goodsDetail",goodsDetails);
            map.put("goodsParam",goodsParams);
            //5.??????Context??????
            Context context=new Context();
            //6.??????????????????,????????????,????????????????????????
            context.setVariables(map);
            //7.????????????
            templateEngine.process("goods",context,fileWriter);
            System.out.println("????????????????????????~~~~");
            fileWriter.close();
        }
        return "done";
    }


    //@Scheduled(cron="* */5 * * * ?")
    @GetMapping("/static_last5Minutes")
    public void staticLast5Minutes() throws IOException {
        List<Goods> goodsList=goodsService.getGoodsLast5Minutes();
        for(Goods goods:goodsList){
            Long goodsId=goods.getGoods_id();
            //1.???????????????????????????
            ClassLoaderTemplateResolver resolver=new ClassLoaderTemplateResolver();
            resolver.setPrefix("templates/");
            resolver.setSuffix(".html");
            //2.??????????????????
            TemplateEngine templateEngine=new TemplateEngine();
            //3.???????????????????????????
            templateEngine.setTemplateResolver(resolver);
            //4.???????????????????????????????????????
            String targetFile="D:/STATIC/"+goodsId+".html";
            FileWriter fileWriter = new FileWriter(targetFile);

            List<GoodsCover> goodsCovers=goodsService.getGoodsCoverById(goodsId);
            List<GoodsDetail> goodsDetails=goodsService.getGoodsDetailById(goodsId);
            List<GoodsParam> goodsParams=goodsService.getGoodsParamById(goodsId);

            //context.setVariable("goods",goods);
            //context.setVariable("goodsCover",goodsCovers);
            //context.setVariable("goodsDetail",goodsDetails);
            //context.setVariable("goodsParam",goodsParams);
            System.out.println("goods:====>"+goods);
            System.out.println("goodsDetail:====>"+goodsDetails);
            Map<String,Object> map=new HashMap<>();
            map.put("goods",goods);
            map.put("goodsCover",goodsCovers);
            map.put("goodsDetail",goodsDetails);
            map.put("goodsParam",goodsParams);
            //5.??????Context??????
            Context context=new Context();
            //6.??????????????????,????????????,????????????????????????
            context.setVariables(map);
            //7.????????????
            templateEngine.process("goods",context,fileWriter);
            System.out.println("????????????????????????~~~~");
            fileWriter.close();
        }
    }


    @GetMapping("/evaluate/{id}")
    @ResponseBody
    public List<Evaluate> getEvaluates(@PathVariable("id") Long id){
      return   goodsService.getGoodsEvaluateById(id);
    }
}
