package com.pratham.ecommerceportal.Controller;

import com.pratham.ecommerceportal.DTO.Request.AddProductDTO;
import com.pratham.ecommerceportal.DTO.Request.PatchDTO;
import com.pratham.ecommerceportal.DTO.Request.PutProductDTO;
import com.pratham.ecommerceportal.DTO.Response.MessageDTO;
import com.pratham.ecommerceportal.DTO.ResponseDTO;
import com.pratham.ecommerceportal.Exception.ProductNotFoundException;
import com.pratham.ecommerceportal.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            return ResponseEntity.internalServerError().body(new MessageDTO(e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getById(@PathVariable UUID id){
        try{
            return ResponseEntity.ok(productService.getProductById(id));
        }catch (ProductNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageDTO(e.getMessage()));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MessageDTO(e.getMessage()));
        }
    }

    @PostMapping("/add")
    public ResponseEntity<ResponseDTO> post(@Valid @RequestBody AddProductDTO addProductDTO){
        try{
            return ResponseEntity.ok(productService.addProduct(addProductDTO));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageDTO(e.getMessage()));
        }
    }

    @PutMapping("/put")
    public ResponseEntity<ResponseDTO> put(@Valid @RequestBody PutProductDTO putProductDTO){
        try{
            return ResponseEntity.ok(productService.putProduct(putProductDTO));
        }catch (ProductNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageDTO(e.getMessage()));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MessageDTO(e.getMessage()));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO> delete(@PathVariable UUID id){
        try{
            return ResponseEntity.ok(productService.deleteProduct(id));
        }catch (ProductNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageDTO(e.getMessage()));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MessageDTO(e.getMessage()));
        }
    }

    @PatchMapping("/patch/{id}")
    public ResponseEntity<ResponseDTO> patch(@PathVariable UUID id, @RequestBody PatchDTO patchDTO){
        try{
            return ResponseEntity.ok(productService.patchProduct(id,patchDTO));
        }catch (ProductNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageDTO(e.getMessage()));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MessageDTO(e.getMessage()));
        }
    }
}
