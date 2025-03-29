package com.opf.order_service.controller;

import com.opf.order_service.ProductClient.ProductClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RESTController {

    @Autowired
    ProductClient productClient;

    @GetMapping("/orders")
    public ResponseEntity<?> getOrderDetails(@RequestParam("id") String id) {
        String productDetails = productClient.getProductDetails(id);

        return new ResponseEntity<>("Order placed for: " + productDetails, HttpStatus.OK);
    }
}
