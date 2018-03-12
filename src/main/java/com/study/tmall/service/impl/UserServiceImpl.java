package com.study.tmall.service.impl;

import com.study.tmall.mapper.UserMapper;
import com.study.tmall.pojo.User;
import com.study.tmall.pojo.UserExample;
import com.study.tmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserMapper userMapper;

    public List<User> list(){
        UserExample example=new UserExample();
        example.setOrderByClause("id desc");
        return userMapper.selectByExample(example);
    }

    public void add(User u){ userMapper.insert(u); }

    public void delete(int id){userMapper.deleteByPrimaryKey(id);}

    public void update(User u){userMapper.updateByPrimaryKeySelective(u);}

    public User get(int id){ return userMapper.selectByPrimaryKey(id);}

    public boolean isExist(String name){
        UserExample example=new UserExample();
        example.createCriteria().andNameEqualTo(name);
        List<User> result=userMapper.selectByExample(example);
        if(!result.isEmpty()) return true;
        return false;
    }

    public User get(String name,String password){
        UserExample example=new UserExample();
        example.createCriteria().andNameEqualTo(name).andPasswordEqualTo(password);
        List<User> result=userMapper.selectByExample(example);
        if(result.isEmpty()) return null;
        return result.get(0);
    }
}
