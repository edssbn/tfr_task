package com.example.tfr.demo.service;

import org.springframework.stereotype.Service;

import com.example.tfr.demo.model.Invoice;
import com.example.tfr.demo.model.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class InvoiceService {

    public List<Invoice> createInvoice(List<Product> products) {
        calculateTotal(products);

        List<Invoice> invoices = new ArrayList<Invoice>();
        return invoices;
    }

    private void calculateTotal(List<Product> products) {
        for (Product product : products) {
            BigDecimal subtotal = product.getPrice().multiply(BigDecimal.valueOf(product.getQuantity()));
            BigDecimal vat = subtotal.multiply(BigDecimal.valueOf(100).divide(BigDecimal.valueOf(product.getTax())));
            BigDecimal total = subtotal.add(vat);
            product.setTotal(total);
            product.setTotalText(bigDecimalFormatter(subtotal) + " + " + bigDecimalFormatter(vat) + " = "
                    + bigDecimalFormatter(total));
        }
    }

    public static String bigDecimalFormatter (BigDecimal num){
        String ret = null;
        try {
            ret = num.toBigIntegerExact().toString();
        } catch (ArithmeticException e){
            num = num.setScale(2,BigDecimal.ROUND_UP); 
            ret = num.toPlainString();
        }
        return ret;
    }

}
