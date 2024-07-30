package com.callthirdparty.service;

import com.callthirdparty.dao.RequestDTO;
import com.callthirdparty.dao.ResponseDTO;
import com.callthirdparty.model.Category;
import com.callthirdparty.model.Product;

import java.util.List;

public interface IProductervice {

     Product getProductById(Long id);
     List<Product> getListOfProduct();
     List<Category> getListOfCatogory();
     List<Product> getProductByCategoryName(String category);
     Product replaceProduct(Long id, RequestDTO requestDTO);
}
