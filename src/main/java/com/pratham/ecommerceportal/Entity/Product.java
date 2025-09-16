package com.pratham.ecommerceportal.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Check(constraints = "price>=0 AND quantity>=0")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "Name can't be blank")
    private String name;

    @Column(nullable = false)
    @NotBlank(message = "Brand can't be blank")
    private String brand;

    private String description;

    @Column(nullable = false)
    @PositiveOrZero(message = "Price must be greater than or equal to zero")
    private BigDecimal price;

    @Column(nullable = false)
    @PositiveOrZero(message = "Quantity must be greater than or equal to zero")
    private int quantity;

}
