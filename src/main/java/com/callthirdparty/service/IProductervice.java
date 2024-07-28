package com.callthirdparty.service;

import com.callthirdparty.dao.ProductDAO;

public interface IProductervice {

     ProductDAO getProductById(Long id);
}
