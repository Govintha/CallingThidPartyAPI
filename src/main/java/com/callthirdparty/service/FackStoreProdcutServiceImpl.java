package com.callthirdparty.service;

import com.callthirdparty.dao.RequestDTO;
import com.callthirdparty.dao.ResponseDTO;
import com.callthirdparty.model.Category;
import com.callthirdparty.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
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

    @Override
    public Product replaceProduct(Long id, RequestDTO requestDTO) {

        //Internal Put method return Void so we cant return so we gone through internal change internal
        // implementation here as we return object example see getForObject implementation
        // we did not getForObject there that accpect only one parameter which return type
        //but here we need change the given object so we write another accpect input Object and returnType
        RequestCallback requestCallback = restTemplate.httpEntityCallback(requestDTO,ResponseDTO.class);
        HttpMessageConverterExtractor<ResponseDTO> responseExtractor =
                new HttpMessageConverterExtractor<>(ResponseDTO.class, restTemplate.getMessageConverters());
        ResponseDTO responseDTO= restTemplate.execute("https://fakestoreapi.com/products/"+id,
                                                        HttpMethod.PUT,
                                                        requestCallback,
                                                        responseExtractor);

        return  getProductFromResponseDTO(responseDTO);
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
