package com.study.tmall.service.impl;

import com.study.tmall.mapper.ProductMapper;
import com.study.tmall.pojo.Category;
import com.study.tmall.pojo.Product;
import com.study.tmall.pojo.ProductExample;
import com.study.tmall.pojo.ProductImage;
import com.study.tmall.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductMapper productMapper;
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductImageService productImageService;
    @Autowired
    OrderItemService orderItemService;
    @Autowired
    ReviewService reviewService;

    //@Override
    public List<Product> list(int cid){
        ProductExample example=new ProductExample();
        example.createCriteria().andCidEqualTo(cid);
        example.setOrderByClause("id desc");
        List l=productMapper.selectByExample(example);
        setCategoryList(l);
        setFirstProductImage(l);
        return l;
    }
    //这一步的意义？可能是为了之后获取Category的代码更简洁
    private void setCategoryList(List<Product> l) { for (Product p : l) setCategoryProduct(p); }
    //同上？
    private void setFirstProductImage(List<Product> l){for(Product p:l) setFirstProductImage(p);}

    //@Override
    public void add(Product p){productMapper.insert(p);}

    //@Override
    public void delete(int id){productMapper.deleteByPrimaryKey(id);}

    //@Override
    public void update(Product p){productMapper.updateByPrimaryKeySelective(p);}

    //@Override
    public Product get(int id){
        Product p=productMapper.selectByPrimaryKey(id);
        setCategoryProduct(p);
        setFirstProductImage(p);
        return p;
    }
    //这一步的意义？可能是为了之后获取Category的代码更简洁
    private void setCategoryProduct(Product p){
        int cid=p.getCid();
        Category c=categoryService.get(cid);
        p.setCategory(c);
    }
    //同上？
    private void setFirstProductImage(Product p){
        List<ProductImage> list=productImageService.list(p.getId(),productImageService.type_single);
        if(!list.isEmpty()) p.setFirstProductImage(list.get(0));
    }

    public void fill(List<Category> cs){for(Category c:cs) fill(c);}

    public void fill(Category c){
        List<Product> list=list(c.getId());
        c.setProducts(list);
    }

    public void fillByRow(List<Category> cs){
        int productNumEachRow=8;
        for(Category c:cs){
            List<Product> products=c.getProducts();
            List<List<Product>> productsByRow=new ArrayList<>();
            for(int i=0;i<products.size();i+=productNumEachRow){
                int size=i+productNumEachRow;
                size=size>products.size()?products.size():size;
                List<Product> productsEachRow=products.subList(i,size);
                productsByRow.add(productsEachRow);
            }
            c.setProductsByRow(productsByRow);
        }
    }

    public void setSaleAndReviewNumberList(List<Product> ps){for(Product p:ps) setSaleAndReviewNumber(p);}

    public void setSaleAndReviewNumber(Product p){
        int saleCount=orderItemService.getSaleCount(p.getId());
        p.setSaleCount(saleCount);
        int reviewCount=reviewService.getCount(p.getId());
        p.setReviewCount(reviewCount);
    }

    public List<Product> search(String keyword) {
        ProductExample example=new ProductExample();
        example.createCriteria().andNameLike("%"+keyword+"%");
        example.setOrderByClause("id desc");
        List<Product> list=productMapper.selectByExample(example);
        setFirstProductImage(list);
        setCategoryList(list);
        return list;
    }
}
