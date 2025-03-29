# Overview

Let's discuss how another service, an Order Service, say `OrderService` can communicate with a Product Service, the `ProductService` in our microservices architecture.

- **Product Service**: Manages product details such as product ID, name, description, price, and inventory.
- **Order Service**: Handles customer orders, which includes creating orders, checking product availability, and reducing product inventory.

By using **RESTful APIs** or **Feign clients**, the `OrderService` can efficiently communicate with the `ProductService` to check product availability and update inventory when creating orders. This setup helps maintain a loosely coupled and scalable microservices architecture.

Let's set up the `OrderServive` first so as to be in the Eureka registry. Afterwards, we will then make it a client of  `ProductService`.

## Configuration: Dependencies

The dependencies we need here are the same with the ones we used to set up `ProductService`. Similar to `ProductService`, below are the necessary configurations for each dependency:

1. **Spring Boot Actuator** (Monitoring & Health Checks):
     + Required Configuration: Enable actuator endpoints in `application.properties` or `application.yml`.
     + **Optional**: Secure actuator endpoints with Spring Security.
2. **Eureka Discovery Client** (Service Discovery/Registry)
     + Required Configuration: Point the microservice to a Eureka Server.
     + **Note**: You must first set up a Eureka Server before your microservices can register.
3. **Spring Cloud Config Client** (Externalized Configuration) - Optional for now
     + Required Configuration: Point the microservice to a Spring Cloud Config Server.
     + **Note**: You must first set up a Config Server before this works.

**Note**: Your microservices should be running on different ports. I have set the port number of `OrderService` to `8082`. The following is the look of the `application.yml` file.

```yml
# Spring Boot Actuator (Monitoring & Health Checks)
management:
  endpoints:
    web:
      exposure:
        include: "*"  # Enable all Actuator endpoints

logging:
  level:
    root: WARN  # Set logging level to WARN
  pattern:
    console: "%clr(%d{yyyy-MM-dd HH:mm:ss}){faint} %clr(%-5level) %clr([%thread]){cyan} %clr(%logger{36}){magenta} - %msg%n"

# Eureka Discovery Client (Service Discovery and Registry)
server:
  port: 8082

spring:
  application:
    name: order-service

eureka:
  client:
    serviceUrl:
      defaultZone: http://localhost:8761/eureka/
```

## Configuration: Spring Boot, Spring Banner, and Loggings

Let's keep these configurations in the `application.properties` file.

```properties
# `spring.application.name` is defined globally here.
spring.application.name=order-service

######
###### SPRING BOOT BANNER, LOGGING CONFIGURATIONS
######

# This will turn off the Spring Boot startup banner
spring.main.banner-mode=OFF

# Set the logging level to WARN for all loggers
logging.level.root=WARN

# Enable ANSI coloring for the logs
spring.output.ansi.enabled=ALWAYS

spring.cloud.config.enabled=false
```

## Enable Discovery Client in Main Class

Modify `OrderServiceApplication.java`.

```java
@SpringBootApplication
@EnableDiscoveryClient  // Enable Eureka Server
public class OrderServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }
}
```


