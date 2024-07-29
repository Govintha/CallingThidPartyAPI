package com.callthirdparty.service;

import com.callthirdparty.dao.ResponseDTO;
import com.callthirdparty.model.Category;
import com.callthirdparty.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FackStoreProdcutServiceImpl implements IProductervice {


    private RestTemplate restTemplate;

    @Autowired
    public FackStoreProdcutServiceImpl(RestTemplate restTemplate){
        this.restTemplate=restTemplate;
    }


    @Override
    public Product getProductById(Long id) {
        ResponseDTO responseDTO= restTemplate.getForObject(
                "https://fakestoreapi.com/products/"+id, ResponseDTO.class);
        return getProductFromResponseDTO(responseDTO);
    }

    @Override
    public List<Product> getListOfProduct() {

         ResponseDTO[] listOfResponseDTO=restTemplate.getForObject("https://fakestoreapi.com/products",ResponseDTO[].class);
         return getListResponseDTOSToListProduct(listOfResponseDTO);

    }

    @Override
    public List<Category> getListOfCatogory() {
        String[] listOfCategory=restTemplate.getForObject("https://fakestoreapi.com/products/categories",String[].class);
        List<Category> lisOfCategory=new ArrayList<>();
        for(String  responseCatogory:listOfCategory){
            Category category = new Category();
            category.setName(responseCatogory);
            lisOfCategory.add(category);
        }
        return lisOfCategory ;
    }

    @Override
    public List<Product> getProductByCategoryName(String categoty) {

        ResponseDTO[] listOfResponseDTO=restTemplate.getForObject("https://fakestoreapi.com/products/category/"+categoty,ResponseDTO[].class);

        return getListResponseDTOSToListProduct(listOfResponseDTO);
    }


    public  Product getProductFromResponseDTO(ResponseDTO responseDTO){

        Product product = new Product();
        Category category = new Category();
        product.setId(responseDTO.getId());
        product.setTitle(responseDTO.getTitle());
        product.setPrice(responseDTO.getPrice());
        product.setDescription(responseDTO.getDescription());
        product.setImage(responseDTO.getImage());
        category.setName(responseDTO.getCategory());
        product.setCategory(category);
        return product;
    }

    public  List<Product> getListResponseDTOSToListProduct(ResponseDTO[] responseDTOS){

        List<Product> listOfProduct=new ArrayList<>();
        for(ResponseDTO responseDTO:responseDTOS){
            listOfProduct.add(getProductFromResponseDTO(responseDTO));
        }
        return listOfProduct;
    }


}
