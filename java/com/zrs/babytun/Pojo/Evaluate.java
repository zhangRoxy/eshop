package com.zrs.babytun.Pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Evaluate {
    private Long evaluate_id;
    private String content;
    private Date create_time;
    private Integer stars;
    private Long goods_id;
}
