package com.zrs.babytun.Pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoodsCover {
    private Long gc_id;
    private Long goods_id;
    private String gc_pic_url;
    private String gc_thumb_url;
    private Long gc_order;
}
