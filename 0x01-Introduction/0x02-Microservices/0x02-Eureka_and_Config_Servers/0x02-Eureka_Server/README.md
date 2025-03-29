# Eureka Server

**Eureka Server** 🏢 is one of the implementations of the ***central service registry for microservices***.

## Create a new Spring Boot project

Create a new Spring Boot project, with name **eureka-server** with the following dependencies:

  - `Spring Boot Actuator`
  - `Spring Cloud Discovery - Eureka Server`

Here’s a table including **Spring Boot Actuator** and **Spring Cloud Discovery - Eureka Server**:

| **Feature**                  | **Category**               | **Exact Dependency Name on start.spring.io** | **Spring Boot Starter / Dependency** | **Purpose** |
|------------------------------|---------------------------|----------------------------------------------|--------------------------------------|------------|
| **Monitoring & Health Checks** | Ops                       | Spring Boot Actuator                        | `spring-boot-starter-actuator`     | Monitor application health, metrics, and sessions |
| **Service Discovery (Server)** | Spring Cloud Discovery    | Eureka Server                               | `spring-cloud-starter-netflix-eureka-server` | Acts as a registry where microservices register and discover each other |

## Configuration

Create `src/main/resources/application.yml` and modify for Eureka Server.

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

# Eureka Discovery Server (Service Discovery)
server:
  port: 8761  # Default port for Eureka Server

eureka:
  instance:
    hostname: localhost
  client:
    register-with-eureka: false  # Eureka Server itself does not register
    fetch-registry: false  # Eureka Server does not fetch other services
```

Let's modify the configuration for the spring banner and the logging in the `application.properties` file.

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

## Enable Eureka Server in Main Class

Modify `EurekaServerApplication.java`.

```java
@SpringBootApplication
@EnableEurekaServer  // Enable Eureka Server
public class EurekaServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class, args);
    }
}
```

## Run the Eureka Server

When running the system of microservices, the central service registry is ran first. To run the Eureka central service registry:

- Run the application (`EurekaServerApplication`).
- Open **`http://localhost:8761`** → You should see the Eureka Dashboard. ✅

Alright. We are done setting up the Eureka server. Let's move on to setting up the external configuration for microservices, Config server. ✅
