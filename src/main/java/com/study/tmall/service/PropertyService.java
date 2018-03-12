package com.study.tmall.service;

import com.study.tmall.pojo.Property;

import java.util.List;

public interface PropertyService {
    List<Property> list(int cid);
    void add(Property property);
    void delete(int id);
    void update(Property property);
    Property get(int id);
}
