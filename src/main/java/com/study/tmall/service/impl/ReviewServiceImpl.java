package com.study.tmall.service.impl;

import com.study.tmall.mapper.ReviewMapper;
import com.study.tmall.pojo.Review;
import com.study.tmall.pojo.ReviewExample;
import com.study.tmall.pojo.User;
import com.study.tmall.service.ReviewService;
import com.study.tmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    ReviewMapper reviewMapper;
    @Autowired
    UserService userService;

    public List<Review> list(int pid){
        ReviewExample example=new ReviewExample();
        example.createCriteria().andPidEqualTo(pid);
        example.setOrderByClause("id desc");
        List<Review> list=reviewMapper.selectByExample(example);
        setUserList(list);
        return list;
    }

    private void setUserList(List<Review> reviews){for(Review r:reviews) setUser(r);}

    private void setUser(Review review){
        User user=userService.get(review.getUid());
        review.setUser(user);
    }

    public void add(Review r){reviewMapper.insert(r);}

    public void delete(int id){reviewMapper.deleteByPrimaryKey(id);}

    public void update(Review r){reviewMapper.updateByPrimaryKeySelective(r);}

    public Review get(int id){return reviewMapper.selectByPrimaryKey(id);}

    public int getCount(int pid){return list(pid).size();}
}
