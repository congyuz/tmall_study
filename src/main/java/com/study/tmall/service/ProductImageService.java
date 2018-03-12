package com.study.tmall.service;

import com.study.tmall.pojo.ProductImage;

import java.util.List;

public interface ProductImageService {
    String type_single="type_single";
    String type_detail="type_detail";
    List<ProductImage> list(int pid,String type);
    void add(ProductImage pi);
    void delete(int id);
    //void update(ProductImage pi);
    ProductImage get(int id);
}
