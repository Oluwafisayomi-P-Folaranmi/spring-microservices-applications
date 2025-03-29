# Overview

Here is an overview of the **Eureka server** and the **Config server** in microservices architecture.

## Eureka Server

Eureka Server is a service registry used for service discovery in a microservices architecture. It allows services to register themselves and discover other services.

## Config Server

Config Server provides centralized configuration management for microservices, allowing them to fetch configuration properties from a centralized location.

## Server Communications

The relationship between **Eureka Server** and **Config Server** in a microservices architecture is complementary, as they serve different but interconnected purposes:

1. **Service Discovery**: Eureka Server handles service discovery. ***When microservices register with Eureka, they can discover and communicate with each other using Eureka's registry***. This ensures that services can find each other dynamically, making the system more resilient and scalable.

2. **Centralized Configuration**: Config Server handles centralized configuration management. ***It provides a central location for managing configuration properties for all microservices***. This ensures that configuration changes can be managed and applied consistently across all services without restarting them.

### How They Work Together:

- **Microservices Registration**: Microservices register themselves with Eureka Server when they start up, providing their network location.
- **Config Fetching**: When a microservice starts, it fetches its configuration properties from the Config Server. The Config Server retrieves these properties from a central repository (e.g., a Git repository).
- **Service Communication**: Microservices use the Eureka Server to discover and communicate with other services. They use the configuration properties fetched from the Config Server to operate according to the environment settings (e.g., database connections, API keys).

In summary, **Eureka Server** ensures microservices can discover each other and **Config Server** ensures they get the right configuration. Together, they enhance the flexibility, maintainability, and scalability of a microservices architecture. 🚀 We will set up two microservices:

1. **Eureka Server** 🏢 → Central service registry for microservices.
2. **Config Server** 📜 → External service for the configuration of microservices.
