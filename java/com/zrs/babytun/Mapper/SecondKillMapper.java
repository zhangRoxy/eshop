package com.zrs.babytun.Mapper;

import com.zrs.babytun.Pojo.SecondKillGoods;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SecondKillMapper {
    List<SecondKillGoods> getUnStartSecondKillGoods();
    SecondKillGoods getSecondSkillById(Integer ps_id);
    List<SecondKillGoods> getFinishedSecondKillGoods();
    void updateSecondKillGoods(SecondKillGoods secondKillGoods);
}
