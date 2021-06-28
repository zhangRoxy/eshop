package com.zrs.babytun.Mapper;

import com.zrs.babytun.Pojo.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface GoodsMapper {
    Goods getGoodsById(Long id);
    List<Goods> getAllGoods();
    List<GoodsCover> getGoodsCoverById(Long id);
    List<GoodsDetail> getGoodsDetailById(Long id);
    List<GoodsParam> getGoodsParamById(Long id);
    List<Evaluate> getGoodsEvaluateById(Long id);
    List<Goods> getGoodsLast5Minutes();

}
