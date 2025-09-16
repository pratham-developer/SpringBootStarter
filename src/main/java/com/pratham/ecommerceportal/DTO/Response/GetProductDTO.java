package com.pratham.ecommerceportal.DTO.Response;

import com.pratham.ecommerceportal.DTO.ResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetProductDTO implements ResponseDTO {
    private UUID id;
    private String name;
    private String brand;
    private String description;
    private BigDecimal price;
    private int quantity;
}
