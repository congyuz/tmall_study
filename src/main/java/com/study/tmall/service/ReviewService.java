package com.study.tmall.service;

import com.study.tmall.pojo.Review;

import java.util.List;

public interface ReviewService {
    List<Review> list(int pid);
    void add(Review review);
    void delete(int id);
    void update(Review review);
    Review get(int id);
    int getCount(int pid);
}
