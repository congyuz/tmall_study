package com.study.tmall.service.impl;

import com.study.tmall.mapper.PropertyMapper;
import com.study.tmall.pojo.Property;
import com.study.tmall.pojo.PropertyExample;
import com.study.tmall.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyServiceImpl implements PropertyService{
    @Autowired
    PropertyMapper propertyMapper;

    //@Override
    public List<Property> list(int cid){
        PropertyExample example=new PropertyExample();
        example.createCriteria().andCidEqualTo(cid);
        return propertyMapper.selectByExample(example);
    }

    //@Override
    public void add(Property p){ propertyMapper.insert(p); }

    //@Override
    public void delete(int id){ propertyMapper.deleteByPrimaryKey(id); }

    //@Override
    public void update(Property p){ propertyMapper.updateByPrimaryKeySelective(p); }

    //@Override
    public Property get(int id){ return propertyMapper.selectByPrimaryKey(id); }
}
