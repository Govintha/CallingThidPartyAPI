package com.callthirdparty.controller;

import com.callthirdparty.dao.ProductDAO;
import com.callthirdparty.model.Category;
import com.callthirdparty.model.Product;
import com.callthirdparty.service.IProductervice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    IProductervice iProductervice;

    public ProductController(IProductervice iProductervice){
        this.iProductervice=iProductervice;
    }


    @GetMapping
    public List<Product> getAllProduct(){

         return null;
    }

    @GetMapping("/{id}")
    public ProductDAO getProductById(@PathVariable("id") Long id){
        return iProductervice.getProductById(id);
    }

    @GetMapping("/categories")
    public  List<Category> getListOfCategory(){
        return null;
    }

    @GetMapping("/categories/{categoryname}")
    public List<Product> getProductByCategoryName(@PathVariable("categoryname")String categoryname){
        return null;
    }

    @PostMapping
    public Product addProduct(@RequestBody ProductDAO product){
        return null;
    }

}
