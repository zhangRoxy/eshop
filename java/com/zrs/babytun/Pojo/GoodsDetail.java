package com.zrs.babytun.Pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoodsDetail {
    private Long gd_id;
    private Long goods_id;
    private String gd_pic_url;
    private Integer gd_order;

}
