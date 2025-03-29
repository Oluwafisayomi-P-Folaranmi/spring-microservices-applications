# Dependencies

To create a microservice application using Spring, we will need some certain Spring Projects with their right starters as dependencies.

For a basic microservice like the **ProductService**, you typically include these starters:

| **Feature**                  | **Category**               | **Exact Dependency Name on start.spring.io** | **Spring Boot Starter / Dependency** | **Purpose** |
|------------------------------|---------------------------|----------------------------------------------|--------------------------------------|------------|
| **REST API Development**      | Web                       | Spring Web                                  | `spring-boot-starter-web`          | Build RESTful APIs using Spring MVC |
| **Monitoring & Health Checks** | Ops                       | Spring Boot Actuator                        | `spring-boot-starter-actuator`     | Monitor application health, metrics, and sessions |
| **Service Discovery**         | Spring Cloud Discovery    | Spring Cloud Discovery - *Eureka Client*                     | `spring-cloud-starter-netflix-eureka-client` | Register microservices in Eureka for service discovery |
| **Distributed Configuration** | Spring Cloud Config       | Spring Cloud Config Client                               | `spring-cloud-starter-config`      | Fetch externalized configuration from Config Server |

When you **search for these exact names** on start.spring.io, you can add them to your project.

These starters help you quickly set up your Spring Boot project with the required dependencies for various functionalities.

## Configuration: Dependencies

After adding these dependencies, you must configure your project properly before it can run successfully. Below are the necessary configurations for each dependency:

1. **Spring Boot Actuator** (Monitoring & Health Checks):
     + Required Configuration: Enable actuator endpoints in `application.properties` or `application.yml`.
     + **Optional**: Secure actuator endpoints with Spring Security.
2. **Eureka Discovery Client** (Service Discovery/Registry)
     + Required Configuration: Point the microservice to a Eureka Server.
     + **Note**: You must first set up a Eureka Server before your microservices can register.
3. **Spring Cloud Config Client** (Externalized Configuration) - Optional for now
     + Required Configuration: Point the microservice to a Spring Cloud Config Server.
     + **Note**: You must first set up a Config Server before this works.

**Note**: Your microservices should be running on different ports. The following is the look of the `application.yml` file.

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
  port: 8081

spring:
  application:
    name: product-service

eureka:
  client:
    serviceUrl:
      defaultZone: http://localhost:8761/eureka/
```

## Configuration: Spring Boot, Spring Banner, and Loggings

Let's keep these configurations in the `application.properties` file.

```properties
# `spring.application.name` is defined globally here.
spring.application.name=product-service

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

Modify `ProductServiceApplication.java`.

```java
@SpringBootApplication
@EnableDiscoveryServer  // Enable Eureka Server
public class ProductServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }
}
```
