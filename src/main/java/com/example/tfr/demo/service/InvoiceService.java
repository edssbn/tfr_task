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
        boolean isPriorityProduct = false;
        Product priorityProduct = new Product();

        while (!products.isEmpty()) {
            BigDecimal total = BigDecimal.ZERO;
            List<Product> singleInvoiceProducts = new ArrayList<>();
            if (isPriorityProduct && singleInvoiceProducts.isEmpty()) {
                isPriorityProduct = false;
                singleInvoiceProducts.add(priorityProduct);
            } else {
                for (Product product : products) {
                    if (calculateProductTotal(product, 1).compareTo(new BigDecimal(500)) > 0) {
                        isPriorityProduct = true;
                        priorityProduct = product;
                    } else {
                        int quantity = determineQuantity(product, total);
                        BigDecimal productTotal = calculateProductTotal(product, quantity);
                        total = total.add(productTotal);
                        if (total.compareTo(new BigDecimal(500)) < 0) {
                            if (product.getQuantity() > quantity) {
                                product.setQuantity(product.getQuantity() - quantity);
                                singleInvoiceProducts
                                        .add(new Product(product.getDescription(), quantity, product.getPrice(),
                                                product.getDiscount(), product.getTax(), ""));
                            } else {
                                singleInvoiceProducts.add(product);
                            }
                        } else {
                            total = total.subtract(productTotal);
                        }
                    }
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

            subtotal = subtotal.setScale(2, RoundingMode.HALF_EVEN);
            tax = tax.setScale(2, RoundingMode.HALF_EVEN);
            total = total.setScale(2, RoundingMode.HALF_EVEN);

            product.setTotal(subtotal + " + " + tax + " = " + total);

            invSubtotal = invSubtotal.add(subtotal);
            invTax = invTax.add(tax);
            invTotal = invTotal.add(total);
        }

        return new Invoice(products, invSubtotal, invTax, invTotal);
    }

    private BigDecimal calculateProductTotal(Product product, int quantity) {

        BigDecimal subtotal = BigDecimal.valueOf(quantity)
                .multiply(product.getPrice().subtract(product.getDiscount()));
        BigDecimal tax = subtotal.multiply(BigDecimal.valueOf(product.getTax()).divide(BigDecimal.valueOf(100)));

        return subtotal.add(tax);
    }

    private int determineQuantity(Product product, BigDecimal total) {

        int quantity = 1;
        for (int i = 1; i <= 50 && i <= product.getQuantity(); i++) {
            if (total.add(calculateProductTotal(product, i)).compareTo(new BigDecimal(500)) < 0) {
                quantity = i;
            }
        }

        return quantity;
    }

}
