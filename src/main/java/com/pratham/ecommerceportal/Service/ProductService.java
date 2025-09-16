package com.pratham.ecommerceportal.Service;

import com.pratham.ecommerceportal.DTO.Request.AddProductDTO;
import com.pratham.ecommerceportal.DTO.Request.PatchDTO;
import com.pratham.ecommerceportal.DTO.Request.PutProductDTO;
import com.pratham.ecommerceportal.DTO.Response.GetAllDTO;
import com.pratham.ecommerceportal.DTO.Response.GetProductDTO;
import com.pratham.ecommerceportal.DTO.Response.MessageDTO;
import com.pratham.ecommerceportal.Entity.Product;
import com.pratham.ecommerceportal.Exception.ProductNotFoundException;
import com.pratham.ecommerceportal.Repository.ProductRepository;
import jakarta.validation.constraints.NotBlank;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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

    public MessageDTO deleteProduct(UUID id){
        Product product = productRepository.findById(id).orElseThrow(()->new ProductNotFoundException());
        productRepository.delete(product);
        return new MessageDTO("product deleted successfully");
    }


    public GetProductDTO patchProduct(UUID id, PatchDTO patchDTO){
        Product product = productRepository.findById(id).orElseThrow(()->new ProductNotFoundException());

        if(patchDTO.getName()!=null && !patchDTO.getName().isBlank()){
            product.setName(patchDTO.getName());
        }
        if(patchDTO.getBrand()!=null && !patchDTO.getBrand().isBlank()){
            product.setBrand(patchDTO.getBrand());
        }
        if(patchDTO.getDescription()!=null && !patchDTO.getDescription().isBlank()){
            product.setDescription(patchDTO.getDescription());
        }
        if (patchDTO.getPrice() != null && patchDTO.getPrice().compareTo(BigDecimal.ZERO) >= 0) {
            product.setPrice(patchDTO.getPrice());
        }

        if (patchDTO.getQuantity() != null && patchDTO.getQuantity() >= 0) {
            product.setQuantity(patchDTO.getQuantity());
        }

        productRepository.save(product);
        return modelMapper.map(product,GetProductDTO.class);

    }

}
