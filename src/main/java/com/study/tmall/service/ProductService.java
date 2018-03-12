package com.study.tmall.service;

import com.study.tmall.pojo.Category;
import com.study.tmall.pojo.Product;

import java.util.List;

public interface ProductService {
    List<Product> list(int cid);
    void add(Product product);
    void delete(int id);
    void update(Product product);
    Product get(int id);
    void fill(Category category);
    void fill(List<Category> categories);
    void fillByRow(List<Category> categories);
    void setSaleAndReviewNumber(Product p);
    void setSaleAndReviewNumberList(List<Product> ps);
    List<Product> search(String keyword);
}
