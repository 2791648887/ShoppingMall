package com.shipping.dao;

import com.shipping.pojo.Order;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    Order selectByUserIdAndOrderNo(@Param("userId") Integer userId, @Param("orderNo") Long orderNo);

    Order selectByOrderNo(Long orderNo);

    List<Order> selectByUserId(Integer userId);

    List<Order> selectAllOrder();

    //定时关闭订单交易
    List<Order> selectOrderStatusByCreateTime(@Param("status") Integer status, @Param("date") String date);

    int closeOrderByOrderId(Integer id);
}