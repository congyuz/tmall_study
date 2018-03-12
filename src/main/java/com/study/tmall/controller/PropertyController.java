package com.study.tmall.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.study.tmall.pojo.Category;
import com.study.tmall.pojo.Property;
import com.study.tmall.service.CategoryService;
import com.study.tmall.service.PropertyService;
import com.study.tmall.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("")
public class PropertyController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    PropertyService propertyService;

    @RequestMapping("admin_property_list")
    //此处cid是由listCategory中的超链接"admin_property_list?cid=${c.id}"传入
    public String list(Model model, Page page,int cid){
        PageHelper.offsetPage(page.getStart(),page.getCount());
        List<Property> ps=propertyService.list(cid);
        int total=(int)new PageInfo<>(ps).getTotal();
        page.setTotal(total);
        page.setParam("&cid="+cid);
        Category c=categoryService.get(cid);
        model.addAttribute("c",c);
        model.addAttribute("ps",ps);
        model.addAttribute("page",page);
        return "admin/listProperty";
    }

    @RequestMapping("admin_property_add")
    public String add(Property p){
        propertyService.add(p);
        return "redirect:admin_property_list?cid="+p.getCid();
    }

    @RequestMapping("admin_property_delete")
    public String delete(int id){
        Property p=propertyService.get(id);
        propertyService.delete(id);
        return "redirect:admin_property_list?cid="+p.getCid();
    }

    @RequestMapping("admin_property_edit")
    public String edit(int id,Model model){
        Property p=propertyService.get(id);
        Category c=categoryService.get(p.getCid());
        p.setCategory(c);
        model.addAttribute("p",p);
        return "admin/editProperty";
    }

    @RequestMapping("admin_property_update")
    public String update(Property p){
        propertyService.update(p);
        return "redirect:admin_property_list?cid="+p.getCid();
    }
}
