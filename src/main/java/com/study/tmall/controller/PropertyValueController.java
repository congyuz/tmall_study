package com.study.tmall.controller;

import com.study.tmall.pojo.Product;
import com.study.tmall.pojo.PropertyValue;
import com.study.tmall.service.ProductService;
import com.study.tmall.service.PropertyValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("")
public class PropertyValueController {
    @Autowired
    PropertyValueService propertyValueService;
    @Autowired
    ProductService productService;

    @RequestMapping("admin_propertyValue_edit")
    public String edit(Model model, int pid){
        //因为导航里需要显示产品的名称和分类的连接，所以需要Product
        Product p=productService.get(pid);
        propertyValueService.init(p);
        List<PropertyValue> pvs=propertyValueService.list(pid);
        model.addAttribute("p",p);
        model.addAttribute("pvs",pvs);
        return "admin/editPropertyValue";
    }

    @RequestMapping("admin_propertyValue_update")
    @ResponseBody
    public String update(PropertyValue pv){
        propertyValueService.update(pv);
        return "success";
    }
}
