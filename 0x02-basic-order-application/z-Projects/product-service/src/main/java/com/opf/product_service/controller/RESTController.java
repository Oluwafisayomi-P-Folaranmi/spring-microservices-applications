package com.opf.product_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RESTController {

    @GetMapping("/products")
    public ResponseEntity<?> getProduct(@RequestParam("id") String id) {

        return new ResponseEntity<>("Product details for ID: " + id, HttpStatus.OK);
    }
}
