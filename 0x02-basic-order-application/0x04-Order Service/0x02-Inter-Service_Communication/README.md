# Inter-Service Communication

Now, let's create another service **Order Service** that communicates with **Product Service**.

## Create a Spring Project

Create a new Spring Project, name `order-service`. Aside the necessary dependencies for a microservice application, another dependency will be required. The dependency is **Feign Client**.

For a basic microservice like the **ProductService**, you typically include the necessary starters and the **Feign Client** starter.:

| **Feature**                  | **Category**               | **Exact Dependency Name on start.spring.io** | **Spring Boot Starter / Dependency** | **Purpose** |
|------------------------------|---------------------------|----------------------------------------------|--------------------------------------|------------|
| **REST API Development**      | Web                       | Spring Web                                  | `spring-boot-starter-web`          | Build RESTful APIs using Spring MVC |
| **Monitoring & Health Checks** | Ops                       | Spring Boot Actuator                        | `spring-boot-starter-actuator`     | Monitor application health, metrics, and sessions |
| **API Gateway**               | Spring Cloud Routing      | Spring Cloud Routing - *Gateway*                                     | `spring-cloud-starter-gateway`     | Route API requests to microservices |
| **Service Discovery**         | Spring Cloud Discovery    | Spring Cloud Discovery - *Eureka Client*                     | `spring-cloud-starter-netflix-eureka-client` | Register microservices in Eureka for service discovery |
| **Distributed Configuration** | Spring Cloud Config       | Spring Cloud Config Client                               | `spring-cloud-starter-config`      | Fetch externalized configuration from Config Server |
| **OpenFeign (Declarative REST Client)** | Spring Cloud Routing | OpenFeign | `spring-cloud-starter-openfeign` | Simplifies HTTP client calls between microservices by providing a declarative REST client. |

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
@FeignClient(name = "product-service")
public interface ProductClient {

    @GetMapping("/api/products/{id}")
    String getProduct(@PathVariable String id);
}
```

Then we can use this to access the API.

```java
@RestController
@RequestMapping("/api")
public OrderController {

    @AutoWired
    ProductClient productClient;

    @GetMapping("/{id}")
    public String getOrderDetails(@PathVariable String id) {
        String productDetails = productClient.getProduct(id);

        return "Order placed for: " + productDetails;
    }
}
```

✅ Now, **`OrderService` can call `ProductService` using Feign Client** instead of hardcoded URLs.
