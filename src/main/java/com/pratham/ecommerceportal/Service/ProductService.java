package com.pratham.ecommerceportal.Service;

import com.pratham.ecommerceportal.DTO.Request.AddProductDTO;
import com.pratham.ecommerceportal.DTO.Request.PutProductDTO;
import com.pratham.ecommerceportal.DTO.Response.ErrorDTO;
import com.pratham.ecommerceportal.DTO.Response.GetAllDTO;
import com.pratham.ecommerceportal.DTO.Response.GetProductDTO;
import com.pratham.ecommerceportal.DTO.ResponseDTO;
import com.pratham.ecommerceportal.Entity.Product;
import com.pratham.ecommerceportal.Exception.ProductNotFoundException;
import com.pratham.ecommerceportal.Repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Autowired
    ProductService(ProductRepository productRepository,ModelMapper modelMapper){
        this.productRepository=productRepository;
        this.modelMapper=modelMapper;
    }

    public GetAllDTO getAllProducts(){
        List<Product> products = productRepository.findAll();
        return new GetAllDTO(products.stream().map(product -> modelMapper.map(product, GetProductDTO.class)).toList());
    }

    public GetProductDTO getProductById(UUID id){
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException());
        return modelMapper.map(product, GetProductDTO.class);
    }

    public GetProductDTO addProduct(AddProductDTO addProductDTO){
        Product product = productRepository.save(modelMapper.map(addProductDTO,Product.class));
        return modelMapper.map(product, GetProductDTO.class);
    }

    public GetProductDTO putProduct(PutProductDTO putProductDTO){
        productRepository.findById(putProductDTO.getId()).orElseThrow(()->new ProductNotFoundException());
        Product product = productRepository.save(modelMapper.map(putProductDTO,Product.class));
        return modelMapper.map(product,GetProductDTO.class);
    }

}
