package com.study.tmall.controller;

import com.github.pagehelper.PageHelper;
import com.study.tmall.comparator.*;
import com.study.tmall.pojo.*;
import com.study.tmall.service.*;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("")
public class ForeController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;
    @Autowired
    ProductImageService productImageService;
    @Autowired
    UserService userService;
    @Autowired
    PropertyValueService propertyValueService;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderItemService orderItemService;
    @Autowired
    ReviewService reviewService;

    @RequestMapping("forehome")
    public String home(Model model){
        List<Category> cs=categoryService.list();
        productService.fill(cs);
        productService.fillByRow(cs);
        model.addAttribute("cs",cs);
        return "fore/home";
    }

    @RequestMapping("foreregister")
    public String register(Model model, User user){
        String name=user.getName();
        name= HtmlUtils.htmlEscape(name);
        user.setName(name);
        boolean exist=userService.isExist(name);
        if(exist){
            String m="用户名已经被注册，不能使用";
            model.addAttribute("msg",m);
            model.addAttribute("user",null);
            return "fore/register";
        }
        userService.add(user);
        return "redirect:registerSuccessPage";
    }

    @RequestMapping("forelogin")
    public String login(@RequestParam("name") String name, @RequestParam("password") String password,
                        Model model, HttpSession session){
        name=HtmlUtils.htmlEscape(name);
        User user=userService.get(name, password);
        if(user==null){
            model.addAttribute("msg","账号密码错误");
            return "fore/login";
        }
        session.setAttribute("user",user);
        return "redirect:forehome";
    }

    @RequestMapping("forelogout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:forehome";
    }

    @RequestMapping("foreproduct")
    public String product(int pid,Model model){
        Product p=productService.get(pid);
        List<ProductImage> single=productImageService.list(pid,productImageService.type_single);
        List<ProductImage> detail=productImageService.list(pid,productImageService.type_detail);
        p.setProductSingleImages(single);
        p.setProductDetailImages(detail);
        List<PropertyValue> pv=propertyValueService.list(pid);
        List<Review> r=reviewService.list(pid);
        productService.setSaleAndReviewNumber(p);
        model.addAttribute("p",p);
        model.addAttribute("pv",pv);
        model.addAttribute("reviews",r);
        return "fore/product";
    }

    @RequestMapping("forecheckLogin")
    @ResponseBody
    public String checkLogin(HttpSession session){
        User user=(User) session.getAttribute("user");
        if(user==null) return "fail";
        return "success";
    }

    @RequestMapping("foreloginAjax")
    @ResponseBody
    public String loginAjax(@RequestParam("name") String name,@RequestParam("password") String password,
                            HttpSession session){
        name=HtmlUtils.htmlEscape(name);
        User user=userService.get(name, password);
        if(user==null) return"fail";
        session.setAttribute("user",user);
        return "success";
    }

    @RequestMapping("forecategory")
    public String category(int cid,String sort,Model model){
        Category c=categoryService.get(cid);
        productService.fill(c);
        productService.setSaleAndReviewNumberList(c.getProducts());
        if(sort!=null){
            switch (sort){
                case "all":
                    Collections.sort(c.getProducts(),new ProductAllComparator());
                    break;
                case "review":
                    Collections.sort(c.getProducts(),new ProductReviewComparator());
                    break;
                case "date" :
                    Collections.sort(c.getProducts(),new ProductDateComparator());
                    break;

                case "saleCount" :
                    Collections.sort(c.getProducts(),new ProductSaleCountComparator());
                    break;
                case "price":
                    Collections.sort(c.getProducts(),new ProductPriceComparator());
                    break;
            }
        }
        model.addAttribute("c",c);
        return "fore/category";
    }

    @RequestMapping("foresearch")
    public String search(String keyword,Model model){
        PageHelper.offsetPage(0,20);
        List<Product> list=productService.search(keyword);
        productService.setSaleAndReviewNumberList(list);
        model.addAttribute("ps",list);
        return "fore/searchResult";
    }

    @RequestMapping("forebuyone")
    public String buyone(int pid,int num,HttpSession session){
        //Product product=productService.get(pid);
        User user=(User)session.getAttribute("user");
        int oiid=0;
        boolean find=false;
        List<OrderItem> ois=orderItemService.listByUser(user.getId());
        for(OrderItem oi:ois){
            if(oi.getProduct().getId().intValue()== pid){
                oi.setNumber(oi.getNumber()+num);
                orderItemService.update(oi);
                find=true;
                oiid=oi.getId();
                break;
            }
        }
        if(!find){
            OrderItem oi=new OrderItem();
            oi.setUid(user.getId());
            oi.setNumber(num);
            oi.setPid(pid);
            orderItemService.add(oi);
            oiid=oi.getId();
        }
        return "redirect:forebuy?oiid="+oiid;
    }

    @RequestMapping("forebuy")
    public String buy(String[] oiids,Model model,HttpSession session){
        List<OrderItem> ois=new ArrayList<>();
        float total=0;
        for(String str:oiids){
            int id=Integer.parseInt(str);
            OrderItem oi=orderItemService.get(id);
            total+=oi.getProduct().getPromotePrice()*oi.getNumber();
            ois.add(oi);
        }
        session.setAttribute("ois",ois);
        model.addAttribute("total",total);
        return "fore/buy";
    }

    @RequestMapping("foreaddCart")
    @ResponseBody
    public String addCart(int pid,int num,Model model,HttpSession session){
        //Product p=productService.get(pid);
        User user=(User)session.getAttribute("user");
        boolean find=false;
        List<OrderItem> ois=orderItemService.listByUser(user.getId());
        for(OrderItem oi:ois){
            if(oi.getProduct().getId().intValue()==pid){
                oi.setNumber(oi.getNumber()+num);
                orderItemService.update(oi);
                find = true;
                break;
            }
        }
        if(!find){
            OrderItem oi=new OrderItem();
            oi.setUid(user.getId());
            oi.setNumber(num);
            oi.setPid(pid);
            orderItemService.add(oi);
        }
        return "success";
    }

    @RequestMapping("forecart")
    public String cart(Model model,HttpSession session){
        User user=(User)session.getAttribute("user");
        List<OrderItem> ois=orderItemService.listByUser(user.getId());
        model.addAttribute("ois",ois);
        return "fore/cart";
    }

    @RequestMapping("forechangeOrderItem")
    @ResponseBody
    public String changeOrderItem(int pid,int num,Model model,HttpSession session){
        User user=(User)session.getAttribute("user");
        if(user==null) return "fail";
        List<OrderItem> ois=orderItemService.listByUser(user.getId());
        for(OrderItem oi:ois){
            if(oi.getProduct().getId().intValue()==pid){
                oi.setNumber(num);
                orderItemService.update(oi);
                break;
            }
        }
        return "success";
    }

    @RequestMapping("foredeleteOrderItem")
    @ResponseBody
    public String deleteOrderItem(int oiid,Model model,HttpSession session){
        User user=(User)session.getAttribute("user");
        if(user==null) return "fail";
        orderItemService.delete(oiid);
        return "success";
    }

    @RequestMapping("forecreateOrder")
    public String createOrder(Order order,Model model,HttpSession session){
        User user=(User)session.getAttribute("user");
        String orderCode=new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date())+ RandomUtils.nextInt(10000);
        order.setOrderCode(orderCode);
        order.setCreateDate(new Date());
        order.setUid(user.getId());
        order.setStatus(orderService.waitPay);
        List<OrderItem> ois=(List<OrderItem>)session.getAttribute("ois");
        float total=orderService.add(order,ois);
        return "redirect:forealipay?oid="+order.getId()+"&total="+total;
    }

    @RequestMapping("forepayed")
    public String payed(int oid,float total,Model model){
        Order order=orderService.get(oid);
        order.setStatus(orderService.waitDelivery);
        order.setPayDate(new Date());
        orderService.update(order);
        model.addAttribute("o",order);
        return "fore/payed";
    }

    @RequestMapping("forebought")
    public String bought(Model model,HttpSession session){
        User user=(User)session.getAttribute("user");
        List<Order> os=orderService.list(user.getId(),orderService.delete);
        orderItemService.fillList(os);
        model.addAttribute("os",os);
        return "fore/bought";
    }

    @RequestMapping("foreconfirmPay")
    public String confirmPay(int oid,Model model){
        Order order = orderService.get(oid);
        orderItemService.fill(order);
        model.addAttribute("o", order);
        return "fore/confirmPay";
    }

    @RequestMapping("foredealSuccess")
    public String dealSuccess(int oid,Model model){
        Order order=orderService.get(oid);
        order.setStatus(orderService.waitReview);
        order.setConfirmDate(new Date());
        orderService.update(order);
        return "fore/dealSuccess";
    }

    @RequestMapping("foredeleteOrder")
    @ResponseBody
    public String deleteOrder( Model model,int oid){
        Order o = orderService.get(oid);
        o.setStatus(OrderService.delete);
        orderService.update(o);
        return "success";
    }

    @RequestMapping("forereview")
    public String review(int oid,Model model){
        Order order=orderService.get(oid);
        orderItemService.fill(order);
        Product product=order.getOrderItems().get(0).getProduct();
        List<Review> list=reviewService.list(product.getId());
        productService.setSaleAndReviewNumber(product);
        model.addAttribute("p",product);
        model.addAttribute("o",order);
        model.addAttribute("reviews",list);
        return "fore/review";
    }

    @RequestMapping("foresubmitreview")
    public String submitreview(@RequestParam("oid")int oid,@RequestParam("pid")int pid,
                               String content, Model model,HttpSession session){
        Order order=orderService.get(oid);
        order.setStatus(orderService.finish);
        orderService.update(order);
        //Product product=productService.get(pid);
        content=HtmlUtils.htmlEscape(content);
        User user=(User)session.getAttribute("user");
        Review review=new Review();
        review.setContent(content);
        review.setPid(pid);
        review.setUid(user.getId());
        review.setCreateDate(new Date());
        reviewService.add(review);
        return "redirect:forereview?oid="+oid+"&showonly=true";
    }
}
