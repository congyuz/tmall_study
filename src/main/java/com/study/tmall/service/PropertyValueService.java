package com.study.tmall.service;

import com.study.tmall.pojo.Product;
import com.study.tmall.pojo.Property;
import com.study.tmall.pojo.PropertyValue;

import java.util.List;

public interface PropertyValueService {
    List<PropertyValue> list(int pid);
    void init(Product p);
    void update(PropertyValue propertyValue);
    PropertyValue get(int propid,int pid);
}
