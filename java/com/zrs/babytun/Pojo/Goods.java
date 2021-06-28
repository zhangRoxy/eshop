package com.zrs.babytun.Pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Goods {
    private Long goods_id;
    private String title;
    private String sub_title;
    private Float original_cost;
    private Float current_price;
    private Float discount;
    private Integer is_free_delivery;
    private Long category_id;

}
