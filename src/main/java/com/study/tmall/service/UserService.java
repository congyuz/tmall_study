package com.study.tmall.service;

import com.study.tmall.pojo.User;

import java.util.List;

public interface UserService {
    List<User> list();
    void add(User u);
    void delete(int id);
    void update(User u);
    User get(int id);
    boolean isExist(String name);
    User get(String name, String password);
}
