package com.zrs.babytun.Mapper;

import com.zrs.babytun.Pojo.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {
    Order getOrderByOrderId(String order_no);
    Integer insertOrder(Order order);
}
