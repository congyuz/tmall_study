package com.study.tmall.service.impl;

import com.study.tmall.mapper.OrderItemMapper;
import com.study.tmall.pojo.*;
import com.study.tmall.service.OrderItemService;
import com.study.tmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemServiceImpl implements OrderItemService{
    @Autowired
    OrderItemMapper orderItemMapper;
    @Autowired
    ProductService productService;

    public List<OrderItem> list(){
        OrderItemExample example=new OrderItemExample();
        example.setOrderByClause("id desc");
        return orderItemMapper.selectByExample(example);
    }

    public void add(OrderItem oi){orderItemMapper.insert(oi);}

    public void delete(int id) {orderItemMapper.deleteByPrimaryKey(id);}
    //updateByPrimaryKeySelective只更新已改的值，其余不变的值不更新
    public void update(OrderItem oi){orderItemMapper.updateByPrimaryKeySelective(oi);}

    public OrderItem get(int id){
        OrderItem oi=orderItemMapper.selectByPrimaryKey(id);
        setProduct(oi);
        return oi;
    }

    private void setProductList(List<OrderItem> list){ for(OrderItem oi:list) setProduct(oi); }

    private void setProduct(OrderItem oi){
        Product p=productService.get(oi.getPid());
        oi.setProduct(p);
    }

    public void fillList(List<Order> list){for(Order o:list) fill(o);}

    public void fill(Order o){
        OrderItemExample example=new OrderItemExample();
        example.createCriteria().andOidEqualTo(o.getId());
        example.setOrderByClause("id desc");
        List<OrderItem> ois=orderItemMapper.selectByExample(example);
        setProductList(ois);
        float total=0;
        int totalNum=0;
        for(OrderItem oi:ois){
            total+=oi.getNumber()*oi.getProduct().getPromotePrice();
            totalNum+=oi.getNumber();
        }
        o.setTotal(total);
        o.setTotalNumber(totalNum);
        o.setOrderItems(ois);
    }

    public int getSaleCount(int pid){
        OrderItemExample example=new OrderItemExample();
        example.createCriteria().andPidEqualTo(pid);
        List<OrderItem> list=orderItemMapper.selectByExample(example);
        int count=0;
        for(OrderItem oi:list) count+=oi.getNumber();
        return count;
    }

    public List<OrderItem> listByUser(int uid){
        OrderItemExample example=new OrderItemExample();
        example.createCriteria().andUidEqualTo(uid).andOidIsNull();
        List<OrderItem> list=orderItemMapper.selectByExample(example);
        setProductList(list);
        return list;
    }
}
