# API Gateway

✅ An **API Gateway** acts as a single entry point for all client requests.

## Create a new Spring Boot project

Create a new Spring Boot project, with name **`api-gateway`** with the following dependencies:

  - `Spring Boot Actuator`
  - `Spring Cloud Discovery - Eureka Client`
  - `Spring Cloud Gateway`

Here’s a table including **Spring Boot Actuator**, **Spring Cloud Discovery - Eureka Client**, and **Spring Cloud Gateway**:

| **Feature**                  | **Category**               | **Exact Dependency Name on start.spring.io** | **Spring Boot Starter / Dependency** | **Purpose** |
|------------------------------|---------------------------|----------------------------------------------|--------------------------------------|------------|
| **Monitoring & Health Checks** | Ops                       | Spring Boot Actuator                        | `spring-boot-starter-actuator`     | Monitor application health, metrics, and sessions |
| **Service Discovery (Server)** | Spring Cloud Discovery    | Eureka Server                               | `spring-cloud-starter-netflix-eureka-server` | Acts as a registry where microservices register and discover each other |
| **Spring Cloud Gateway**         | Routing                    | `spring-cloud-starter-gateway`                 | Dependency                           | Provides a simple yet powerful way to route and filter requests to backend services. Ideal for implementing an API Gateway with advanced features like rate limiting, path rewriting, and more. |

## Configuration

Modify `APIGatewayApplication.java`:

```java
@SpringBootApplication
@EnableDiscoveryClient
public class APIGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(APIGatewayApplication.class, args);
    }
}
```

The next thing is to configure routes in `application.yml`.

```yml
server:
  port: 8080

spring:
  application:
    name: api-gateway

eureka:
  client:
    serviceUrl:
      defaultZone: http://localhost:8761/eureka/

  cloud:
    gateway:
      routes:
        - id: product-service
          uri: lb://PRODUCT-SERVICE
          predicates:
            - Path=/products/**
        
        - id: order-service
          uri: lb://ORDER-SERVICE
          predicates:
            - Path=/orders/**
```

**API Gateway now routes traffic based on paths:**  
  - `/products/**` → **Product Service**  
  - `/orders/**` → **Order Service**  

Let's modify the configuration for spring banner and logging in the `application.properties` file.

```properties
spring.application.name=eureka-server

######
###### SPRING BOOT BANNER, LOGGING CONFIGURATIONS
######

# This will turn off the Spring Boot startup banner
spring.main.banner-mode=OFF

# Set the logging level to WARN for all loggers
logging.level.root=WARN

# Enable ANSI coloring for the logs
spring.output.ansi.enabled=ALWAYS
```

## Testing the System

Start the services in this order:**

  1. **Eureka Server** (`http://localhost:8761`)  
  2. **Product Service** (`http://localhost:8081`)  
  3. **Order Service** (`http://localhost:8082`)  
  4. **API Gateway** (`http://localhost:8080`) 

Why **API Gateway**? Now, you can access APIs through Gateway by using the port `8080`:

  - **Product Service**  
    ```
    GET http://localhost:8080/api/products?id=1
    ```
  - **Order Service**  
    ```
    GET http://localhost:8080/api/orders?id=1
    ```
