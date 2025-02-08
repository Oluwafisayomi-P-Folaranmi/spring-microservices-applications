# Config Server

The **Config Server** manages external configuration for microservices.  

## Create a new Spring Boot project

Create a new Spring Boot project, with name **config-server** with the following dependencies:

  - `Spring Boot Actuator`
  - `Spring Cloud Config Server`

Here’s the **new table** including **Spring Boot Actuator** and **Spring Cloud Config Server**:

| **Feature**                     | **Category**           | **Exact Dependency Name on start.spring.io** | **Spring Boot Starter / Dependency** | **Purpose** |
|----------------------------------|-----------------------|----------------------------------------------|--------------------------------------|------------|
| **Monitoring & Health Checks**   | Ops                   | Spring Boot Actuator                        | `spring-boot-starter-actuator`     | Monitor application health, metrics, and sessions |
| **Externalized Configuration**   | Spring Cloud Config   | Config Server                               | `spring-cloud-starter-config`      | Provides a central configuration server for distributed applications |

## Configuration

Create and modify `src/main/resources/application.yml` for Config Server

```yml
server:
  port: 8888  # Default port for Config Server

spring:
  cloud:
    config:
      server:
        git:
          uri: https://github.com/your-repo/spring-config  # Replace with your Git repo
          default-label: main  # Branch name
  application:
    name: config-server  # Name of the config server

eureka:
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka  # Register Config Server in Eureka
```

**Replace** `https://github.com/your-repo/spring-config` with your actual Git repository URL.

Let's modify the configuration for the spring banner and the logging in the `application.properties` file.

```properties
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

## Enable Config Server in Main Class

Modify `ConfigServerApplication.java`

```java
package com.example.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer  // Enable Config Server
public class ConfigServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class, args);
    }
}
```

## Run the Config Server

- Start `Eureka Server` first.
- Afterwards, run `ConfigServerApplication.java`
- Open **`http://localhost:8888/product-service/default`** → You should see configurations from Git. ✅
