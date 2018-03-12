package com.study.tmall.service.impl;

import com.study.tmall.mapper.CategoryMapper;
import com.study.tmall.pojo.Category;
import com.study.tmall.pojo.CategoryExample;
import com.study.tmall.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    CategoryMapper categoryMapper;

    //@Override
    public List<Category> list(){
        CategoryExample example=new CategoryExample();
        example.setOrderByClause("id desc");
        return categoryMapper.selectByExample(example);
    }

    //@Override
    public void add(Category c){categoryMapper.insert(c);}

    //@Override
    public void delete(int id){categoryMapper.deleteByPrimaryKey(id);}

    //@Override
    public Category get(int id) { return categoryMapper.selectByPrimaryKey(id); }

    //@Override
    public void update(Category c){categoryMapper.updateByPrimaryKeySelective(c);}
}
