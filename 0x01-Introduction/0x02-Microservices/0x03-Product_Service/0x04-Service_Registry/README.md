# Service Registry

The procedure through:

1. Central service registry
2. External configuration, and
3. Creation of a microservice

will not be connected if it lacks **Service Registry**. Here we will proceed to one of the most important step in creating microservices systems, **Service Registry**.

The dependency **Spring Cloud Discovery - *Eureka Client*** is used to register a microservice as a client of a central service registry, like Eureka Server. We have already confgured our service to register itself in Eureka Server. This is done in the `application.yml` file as seen from previous section.

```yml
server:
  port: 8081

spring:
  application:
    name: product-service

eureka:
  client:
    serviceUrl:
      defaultZone: http://localhost:8761/eureka/
```

## Enable Eureka Client in Main Class

```java
@SpringBootApplication
@EnableDiscoveryClient
public class ProductServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }
}
```

Now, the **Product Service** registers itself with **Eureka** ✅.
