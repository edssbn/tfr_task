package com.example.tfr.demo.service;

import org.springframework.stereotype.Service;

import com.example.tfr.demo.model.Invoice;
import com.example.tfr.demo.model.Product;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
public class InvoiceService {

    public List<Invoice> createInvoices(List<Product> products) {

        List<Invoice> invoices = new ArrayList<>();

        while (!products.isEmpty()) {
            BigDecimal total = BigDecimal.ZERO;
            List<Product> singleInvoiceProducts = new ArrayList<>();
            for (Product product : products) {
                BigDecimal productTotal = calculateProductTotal(product);
                total = total.add(productTotal);
                if (total.compareTo(new BigDecimal(500)) < 0 || singleInvoiceProducts.isEmpty()) {
                    if (product.getQuantity() > 50) {
                        product.setQuantity(product.getQuantity() - 50);
                        singleInvoiceProducts.add(new Product(product.getName(), 50, product.getPrice(),
                                product.getDiscount(), product.getTax(), ""));
                    } else {
                        singleInvoiceProducts.add(product);
                    }
                } else {
                    total = total.subtract(productTotal);
                }
            }
            invoices.add(generateInvoice(singleInvoiceProducts));
            products.removeAll(singleInvoiceProducts);
        }

        return invoices;
    }

    private Invoice generateInvoice(List<Product> products) {

        BigDecimal invSubtotal = BigDecimal.ZERO;
        BigDecimal invTax = BigDecimal.ZERO;
        BigDecimal invTotal = BigDecimal.ZERO;

        for (Product product : products) {
            BigDecimal subtotal = BigDecimal.valueOf(product.getQuantity())
                    .multiply(product.getPrice().subtract(product.getDiscount()));
            BigDecimal tax = subtotal.multiply(BigDecimal.valueOf(product.getTax()).divide(BigDecimal.valueOf(100)));
            BigDecimal total = subtotal.add(tax);

            subtotal = subtotal.setScale(2, RoundingMode.CEILING);
            tax = tax.setScale(2, RoundingMode.CEILING);
            total = total.setScale(2, RoundingMode.CEILING);

            product.setTotal((subtotal) + " + " + (tax) + " = " + (total));

            invSubtotal = invSubtotal.add(subtotal);
            invTax = invTax.add(tax);
            invTotal = invTotal.add(total);
        }

        return new Invoice(products, invSubtotal, invTax, invTotal);
    }

    private BigDecimal calculateProductTotal(Product product) {

        Integer quantity = Math.min(50, product.getQuantity());
        BigDecimal subtotal = BigDecimal.valueOf(quantity)
                .multiply(product.getPrice().subtract(product.getDiscount()));
        BigDecimal tax = subtotal.multiply(BigDecimal.valueOf(product.getTax()).divide(BigDecimal.valueOf(100)));
        return subtotal.add(tax);
    }

}
