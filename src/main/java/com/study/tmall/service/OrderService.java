package com.study.tmall.service;

import com.study.tmall.pojo.Order;
import com.study.tmall.pojo.OrderItem;

import java.util.List;

public interface OrderService {
    String waitPay="waitPay";
    String waitDelivery="waitDelivery";
    String waitConfirm="waitConfirm";
    String waitReview="waitReview";
    String finish="finish";
    String delete="delete";
    List<Order> list();
    void add(Order order);
    void delete(int id);
    void update(Order order);
    Order get(int id);
    float add(Order o, List<OrderItem>ois);
    List<Order> list(int uid,String excludedStatus);
}
