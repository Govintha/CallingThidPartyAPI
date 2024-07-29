package com.callthirdparty.service;

import com.callthirdparty.dao.ResponseDTO;
import com.callthirdparty.model.Category;
import com.callthirdparty.model.Product;

import java.util.List;

public interface IProductervice {

     Product getProductById(Long id);
     List<Product> getListOfProduct();
     List<Category> getListOfCatogory();
     List<Product> getProductByCategoryName(String category);
}
