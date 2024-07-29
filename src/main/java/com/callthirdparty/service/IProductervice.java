package com.callthirdparty.service;

import com.callthirdparty.dao.ResponseDTO;
import com.callthirdparty.model.Product;

import java.util.List;

public interface IProductervice {

     Product getProductById(Long id);
     List<Product> getListOfProduct();
}
