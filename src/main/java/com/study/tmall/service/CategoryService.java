package com.study.tmall.service;

import com.study.tmall.pojo.Category;

import java.util.List;

public interface CategoryService {
    List<Category> list();
    void add(Category category);
    void delete(int id);
    void update(Category category);
    Category get(int id);
}
