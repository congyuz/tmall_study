package com.study.tmall.service.impl;

import com.study.tmall.mapper.PropertyValueMapper;
import com.study.tmall.pojo.Product;
import com.study.tmall.pojo.Property;
import com.study.tmall.pojo.PropertyValue;
import com.study.tmall.pojo.PropertyValueExample;
import com.study.tmall.service.PropertyService;
import com.study.tmall.service.PropertyValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyValueServiceImpl implements PropertyValueService{
    @Autowired
    PropertyValueMapper propertyValueMapper;
    @Autowired
    PropertyService propertyService;

    //@Override
    public List<PropertyValue> list(int pid){
        PropertyValueExample example=new PropertyValueExample();
        example.createCriteria().andPidEqualTo(pid);
        List<PropertyValue> l=propertyValueMapper.selectByExample(example);
        for(PropertyValue pv:l){
            Property p=propertyService.get(pv.getPtid());
            pv.setProperty(p);
        }
        return l;
    }

    public void init(Product p){
        List<Property> list=propertyService.list(p.getCid());
        for(Property pty:list){
            PropertyValue pv=get(pty.getId(),p.getId());
            if(pv==null){
                pv=new PropertyValue();
                pv.setPid(p.getId());
                pv.setPtid(pty.getId());
                propertyValueMapper.insert(pv);
            }
        }
    }

    public void update(PropertyValue pv){ propertyValueMapper.updateByPrimaryKeySelective(pv); }

    public PropertyValue get(int ptyid,int pid){
        PropertyValueExample example=new PropertyValueExample();
        example.createCriteria().andPidEqualTo(pid).andPtidEqualTo(ptyid);
        List<PropertyValue> l=propertyValueMapper.selectByExample(example);
        if(l.isEmpty()) return null;
        return l.get(0);
    }
}
