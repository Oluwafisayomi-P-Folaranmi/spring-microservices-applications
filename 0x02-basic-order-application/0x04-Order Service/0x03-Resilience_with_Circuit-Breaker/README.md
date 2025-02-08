# Resilience with Circuit Breaker

If **`ProductService`** goes down, we should prevent failures from breaking the entire system. We can accomplish this using the **Circuit Breaker called Resilience4J**.

### Add the dependency for **Circuit Breaker Resilience4J**

The name on `start.spring.io` is **Spring Cloud Circuit Breaker - Resilience4J**.

```xml
<dependency>
      <groupId>org.springframework.cloud</groupId>
      <artifactId>spring-cloud-starter-circuitbreaker-resilience4j</artifactId>
</dependency>
```

### Add the Circuit Breaker to Feign Client

Modify `ProductClient.java`:
```java
@FeignClient(name = "product-service")
public interface ProductClient {

    @GetMapping("/products/{id}")
    @CircuitBreaker(name = "productService", fallbackMethod = "fallbackGetProduct")
    String getProduct(@PathVariable String id);

    default String fallbackGetProduct(String id, Throwable ex) {
        return "Product service is unavailable. Please try again later.";
    }
}
```

✅ If **Product Service** is down, **fallbackGetProduct()** will be used instead.
