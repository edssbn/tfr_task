package com.example.tfr.demo.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Product {
    private String name;

    private Integer quantity;

    private BigDecimal price;

    private BigDecimal discount;
    
    private Integer tax;

    private String total;
}
