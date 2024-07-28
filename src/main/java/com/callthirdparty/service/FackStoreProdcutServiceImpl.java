package com.callthirdparty.service;

import com.callthirdparty.dao.ProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FackStoreProdcutServiceImpl implements IProductervice {

    @Autowired
    private RestTemplate restTemplate;

    public FackStoreProdcutServiceImpl(RestTemplate restTemplate){
        this.restTemplate=restTemplate;
    }


    @Override
    public ProductDAO getProductById(Long id) {
        return restTemplate.getForObject(
                "https://fakestoreapi.com/products/"+id, ProductDAO.class);
    }
}
