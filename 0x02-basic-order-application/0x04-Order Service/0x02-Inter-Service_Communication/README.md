# Inter-Service Communication

We have created the second service, `OrderService`. Now, let's make `OrderService` communicate with `ProductService`.

## Feign Client

**Feign Client** is a declarative web service client that simplifies the process of creating HTTP clients in microservice architectures.

Developed by **Netflix**, ***Feign allows developers to define the structure of web requests through annotated Java interfaces, reducing boilerplate code and enhancing readability***. By integrating with popular frameworks like Spring Cloud, Feign makes it easy to invoke RESTful services, handle request parameters, headers, and even provide custom configurations. Its seamless integration with service discovery and load balancing tools further enables efficient communication between microservices.

## Enable Feign Client in Main Class

```java
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class OrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}
}
```

## Create a Feign Client

Creating this logic conceptually:

1. **Product Service API**: We are ensured that we have the `ProductService` exposed some of its endpoints for product operations.
2. **Order Service Feign Client**: Then we can now define a Feign client to call `ProductService` endpoints.
3. **Order Service Implementation**: Use the Feign client to communicate with the Product Service.

Create a Feign client that `OrderService` will use to call `ProductService`. The Feign client is annotated with the name of the api, here `ProductService` that we want it to call, here `product-service` as in the configuration. Let's say `product-service` is base URL of the `ProductService` API.

```java
@FeignClient(name = "product-service", url = "http://localhost:8081")
public interface ProductClient {

    @GetMapping("/api/products")
    String getProduct(@RequestParam String id);
}
```

Then we can use a **REST controller** to access the API.

```java
@RestController
@RequestMapping("/api")
public class RESTController {

    @Autowired
    ProductClient productClient;

    @GetMapping("/orders")
    public ResponseEntity<?> getOrderDetails(@RequestParam("id") String id) {
        String productDetails = productClient.getProduct(id);

        return new ResponseEntity<>("Order placed for: " + productDetails, HttpStatus.OK);
    }
}
```

✅ Now, **`OrderService` can call `ProductService` using Feign Client** instead of hardcoded URLs.
