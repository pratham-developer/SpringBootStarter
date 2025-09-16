package com.pratham.ecommerceportal.Controller;

import com.pratham.ecommerceportal.DTO.Response.ErrorDTO;
import com.pratham.ecommerceportal.DTO.ResponseDTO;
import com.pratham.ecommerceportal.Exception.ProductNotFoundException;
import com.pratham.ecommerceportal.Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/all")
    public ResponseEntity<ResponseDTO> getAll(){
        try{
            return ResponseEntity.ok(productService.getAllProducts());
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(new ErrorDTO(e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getById(@PathVariable UUID id){
        try{
            return ResponseEntity.ok(productService.getProductById(id));
        }catch (ProductNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDTO(e.getMessage()));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorDTO(e.getMessage()));
        }
    }
}
