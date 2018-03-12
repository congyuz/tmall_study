package com.study.tmall.service;

import com.study.tmall.pojo.Order;
import com.study.tmall.pojo.OrderItem;

import java.util.List;

public interface OrderItemService {
    List<OrderItem> list();
    void add(OrderItem oi);
    void delete(int id);
    void update(OrderItem oi);
    OrderItem get(int id);
    void fillList(List<Order> list);
    void fill(Order order);
    int getSaleCount(int pid);
    List<OrderItem> listByUser(int uid);
}
