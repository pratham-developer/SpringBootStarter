package com.pratham.ecommerceportal.DTO.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PutProductDTO{

    @NotNull
    private UUID id;

    @NotBlank(message = "Product name is required")
    private String name;

    @NotBlank(message = "Brand name is required")
    private String brand;

    private String description;

    @NotNull(message = "Price is required")
    @PositiveOrZero(message = "Price must be zero or positive")
    private BigDecimal price;

    @PositiveOrZero(message = "Quantity must be zero or positive")
    private int quantity;
}
