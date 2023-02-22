package com.example.tfr.demo.model;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Invoice {
    private List<Product> products;

    private BigDecimal subtotal;

    private BigDecimal tax;

    private BigDecimal total;
}
