package com.zrs.babytun.Service;

import com.zrs.babytun.Mapper.GoodsMapper;
import com.zrs.babytun.Pojo.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GoodsService {
    @Resource
    GoodsMapper goodsMapper;

    public Goods getGoodsById(Long goodsId) {
        return goodsMapper.getGoodsById(goodsId);
    }

    public List<Goods> getAllGoods() {
        return goodsMapper.getAllGoods();
    }

    public List<Goods> getGoodsLast5Minutes() {
        return goodsMapper.getGoodsLast5Minutes();
    }

    public List<GoodsCover> getGoodsCoverById(Long goodsId) {
        return goodsMapper.getGoodsCoverById(goodsId);
    }


    public List<GoodsDetail> getGoodsDetailById(Long goodsId) {
        return goodsMapper.getGoodsDetailById(goodsId);
    }

    public List<GoodsParam> getGoodsParamById(Long goodsId) {
        return goodsMapper.getGoodsParamById(goodsId);
    }

    public List<Evaluate> getGoodsEvaluateById(Long goodsId) {
        return goodsMapper.getGoodsEvaluateById(goodsId);
    }
}
