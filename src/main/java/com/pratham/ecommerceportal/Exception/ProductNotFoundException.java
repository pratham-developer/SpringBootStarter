package com.pratham.ecommerceportal.Exception;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(){
        super("Product ID not found");
    }
}
