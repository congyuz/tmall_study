package com.study.tmall.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.study.tmall.pojo.Category;
import com.study.tmall.service.CategoryService;
import com.study.tmall.util.ImageUtil;
import com.study.tmall.util.Page;
import com.study.tmall.util.UploadedImgFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @RequestMapping("admin_category_list")
    public String list(Model model, Page page){
        PageHelper.offsetPage(page.getStart(),page.getCount());
        List<Category> cs = categoryService.list();
        int total = (int)new PageInfo<>(cs).getTotal();
        page.setTotal(total);
        model.addAttribute("cs", cs);
        model.addAttribute("page",page);
        return "admin/listCategory";
    }

    @RequestMapping("admin_category_add")
    public String add(Category c, HttpSession session, UploadedImgFile f) throws IOException{
        categoryService.add(c);
        //getServletContext()通过session获取ControllerContext
        //该File对象是路径
        File imageFolder=new File(session.getServletContext().getRealPath("img/category"));
        //该File对象是具体图片
        File file=new File(imageFolder,c.getId()+".jpg");
        if(!file.getParentFile().exists()) file.getParentFile().mkdirs();
        f.getImage().transferTo(file);
        BufferedImage img= ImageUtil.change2jpg(file);
        ImageIO.write(img,"jpg",file);
        return "redirect:admin_category_list";
    }

    @RequestMapping("admin_category_delete")
    public String delete(int id,HttpSession session) throws IOException{
        categoryService.delete(id);
        File imageFolder=new File(session.getServletContext().getRealPath("img/category"));
        File file=new File(imageFolder,id+".jpg");
        file.delete();
        return "redirect:admin_category_list";
    }

    @RequestMapping("admin_category_edit")
    public  String edit(int id,Model model) throws IOException{
        Category c=categoryService.get(id);
        model.addAttribute("c",c);
        return "admin/editCategory";
    }

    @RequestMapping("admin_category_update")
    public String update(Category c,HttpSession session,UploadedImgFile f) throws IOException{
        categoryService.update(c);
        MultipartFile image=f.getImage();
        if(image!=null&&!image.isEmpty()) {
            File imagefolder = new File(session.getServletContext().getRealPath("img/category"));
            File file = new File(imagefolder, c.getId() + ".jpg");
            image.transferTo(file);
            BufferedImage img = ImageUtil.change2jpg(file);
            ImageIO.write(img, "jpg", file);
        }
        return "redirect:admin_category_list";
    }
}
