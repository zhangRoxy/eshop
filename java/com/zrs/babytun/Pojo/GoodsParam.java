package com.zrs.babytun.Pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoodsParam {
    private Long gp_id;
    private String gp_param_name;
    private String gp_param_value;
    private Long goods_id;
    private Integer gp_order;
}
