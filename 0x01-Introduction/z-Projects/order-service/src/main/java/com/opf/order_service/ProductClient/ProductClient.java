package com.opf.order_service.ProductClient;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "product-service", url = "http://localhost:8081")
@CircuitBreaker(name = "productServiceFallback", fallbackMethod = "productDetailsFallbackMethod")
public interface ProductClient {

    @GetMapping("/api/products")
    String getProductDetails(@RequestParam String id);

    default String productDetailsFallbackMethod(String id, Throwable ex) {
        return "Product service is unavailable. Please try again later.";
    }
}
