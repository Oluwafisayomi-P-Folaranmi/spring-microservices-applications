package com.opf.order_service.controller;

import com.opf.order_service.ProductServiceClient.ProductClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class OrderController {

    @Autowired
    ProductClient productClient;

    @GetMapping("/{id}")
    public String getOrderDetails(@PathVariable String id) {

        String productDetails = productClient.getProduct(id);

        return "Order placed for: " + productDetails;
    }
}
