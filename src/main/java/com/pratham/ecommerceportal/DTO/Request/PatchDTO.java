package com.pratham.ecommerceportal.DTO.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatchDTO {
    private String name;
    private String brand;
    private String description;
    private BigDecimal price;
    private Integer quantity;
}
