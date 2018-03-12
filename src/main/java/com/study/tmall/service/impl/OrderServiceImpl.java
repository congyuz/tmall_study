package com.study.tmall.service.impl;

import com.study.tmall.mapper.OrderMapper;
import com.study.tmall.pojo.Order;
import com.study.tmall.pojo.OrderExample;
import com.study.tmall.pojo.OrderItem;
import com.study.tmall.pojo.User;
import com.study.tmall.service.OrderItemService;
import com.study.tmall.service.OrderService;
import com.study.tmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    UserService userService;
    @Autowired
    OrderItemService orderItemService;

    public List<Order> list(){
        OrderExample example=new OrderExample();
        example.setOrderByClause("id desc");
        List<Order> list=orderMapper.selectByExample(example);
        setUserList(list);
        return list;
    }

    private void setUserList(List<Order> os){for(Order o:os)setUser(o);}

    private void setUser(Order o){
        User u=userService.get(o.getUid());
        if(u==null) System.out.println("user为空");
        else o.setUser(u);
    }

    public void add(Order o){orderMapper.insert(o);}

    public void delete(int id){orderMapper.deleteByPrimaryKey(id);}
    //updateByPrimaryKeySelective只更新已改的值，其余不变的值不更新
    public void update(Order o){orderMapper.updateByPrimaryKeySelective(o);}

    public Order get(int id){return orderMapper.selectByPrimaryKey(id); }

    @Transactional(propagation = Propagation.REQUIRED,rollbackForClassName = "Exception")
    public float add(Order o, List<OrderItem> ois){
        float total=0;
        add(o);
        if(false) throw new RuntimeException();
        for(OrderItem oi:ois){
            oi.setOid(o.getId());
            orderItemService.update(oi);
            total+=oi.getProduct().getPromotePrice()*oi.getNumber();
        }
        return total;
    }

    public List<Order> list(int uid, String excludedStatus) {
        OrderExample example=new OrderExample();
        example.createCriteria().andUidEqualTo(uid).andStatusNotEqualTo(excludedStatus);
        example.setOrderByClause("id desc");
        return orderMapper.selectByExample(example);
    }
}
