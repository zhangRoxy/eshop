package com.zrs.babytun.Pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private Long order_id;
    private String order_no;
    private Integer order_status;
    private String user_id;
    private String recv_name;
    private String recv_address;
    private String recv_mobile;
    private Float postage;
    private Float amout;
    private Date create_time;
}
