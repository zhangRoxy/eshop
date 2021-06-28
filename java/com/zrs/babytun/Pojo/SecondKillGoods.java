package com.zrs.babytun.Pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SecondKillGoods {
    private Integer ps_id;
    private Long goods_id;
    private Integer ps_count;
    private Date start_time;
    private Date end_time;
    private Integer status;
    private Float current_price;
}
