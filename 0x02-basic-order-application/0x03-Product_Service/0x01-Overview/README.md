# Overview

The **Product service** may be the microservice that manages product catalog, inventory, and pricing, say in an **eCommerce application**. It will be named ***ProductService***.

In the **Product Service** Section, we will go through how the **ProductService** microservice application is set up. In the context of microservices and software architecture, a **service** is a distinct, self-contained unit of functionality that performs a specific task or set of tasks within an application. Each service represents a business capability and is typically responsible for a single aspect of the application's operations.

### Key Characteristics of a Service:

1. **Independence**: Services are independent units that can be developed, deployed, and scaled individually.
2. **Single Responsibility**: Each service focuses on a specific business function or domain, adhering to the Single Responsibility Principle.
3. **Loose Coupling**: Services are loosely coupled, meaning changes in one service do not directly impact others, promoting flexibility and maintainability.
4. **Communication**: Services communicate with each other through well-defined APIs (Application Programming Interfaces), often using protocols like HTTP/HTTPS, gRPC, or message brokers.
5. **Autonomy**: Services have their own data storage and business logic, allowing them to operate autonomously.

### Example:
Consider an e-commerce application that might include the following services:
- **User Service**: Manages user accounts, authentication, and authorization.
- **Product Service**: Manages product catalog, inventory, and pricing.
- **Order Service**: Handles order placement, processing, and tracking.
- **Payment Service**: Manages payment processing and transactions.

Each of these services performs a specific function, and they work together to deliver the overall functionality of the e-commerce application. This modular approach makes it easier to develop, deploy, and scale each component independently.

## Service Discovery

At this point, we will set up a ***service discovery*** using Netflix Eureka. Service discovery is a key concept in microservices architecture. It refers to the process of automatically detecting and keeping track of the network locations of service instances, and ensuring that services can find and communicate with each other efficiently.

### Why Service Discovery is Important:

1. **Dynamic Environments**: In microservices, service instances can be dynamically created and destroyed (e.g., due to scaling, failures, or updates). Service discovery helps keep track of these changes.
2. **Decoupling Services**: It allows services to discover each other without hardcoding IP addresses, making the system more flexible and easier to manage.
3. **Load Balancing**: It enables effective load balancing by distributing requests across multiple instances of a service.

### Types of Service Discovery:

1. **Client-Side Discovery**: In this approach, the client is responsible for determining the network locations of available service instances and making requests. An example of a client-side service discovery tool is Netflix Eureka.
2. **Server-Side Discovery**: Here, the client makes a request to a service through a load balancer, and the load balancer is responsible for determining the available service instances. Kubernetes uses server-side discovery with its built-in load balancer.

### How Service Discovery Works:

1. **Service Registry (e.g., Netflix Eureka)**: A central registry keeps track of all available service instances, their locations (IP addresses and ports), and other metadata. Services register themselves with this registry upon startup and deregister when they shut down.
2. **Service Registration**: When a new instance of a service is started, it registers its details with the service registry.
3. **Service Lookup**: When a client wants to call a service - in a microservices architecture, one service might act as a client when it makes a request to another service - it queries the service registry to get the current network location of the service instance.

### Popular Service Discovery Tools:
- **Netflix Eureka**: A client-side service discovery server.
- **Consul**: A tool for service discovery, configuration, and orchestration.
- **Apache ZooKeeper**: A centralized service for maintaining configuration information, naming, and providing distributed synchronization.
- **Kubernetes**: Uses built-in service discovery mechanisms to manage services within a Kubernetes cluster.

In essence, service discovery ensures that microservices can find each other and communicate effectively, making the entire system robust and scalable.

## Service Registry

A **Service Registry** is a centralized database or directory that keeps track of services available in a network. It plays a crucial role in microservices architecture by providing a mechanism for service discovery, allowing services to find and communicate with each other efficiently. Here’s how it works:

1. **Registration**: When a service starts up, it registers itself with the service registry, providing its location (e.g., URL, IP address, port number) and other metadata.

2. **Discovery**: When a service needs to communicate with another service, it queries the service registry to find the location of the required service. This process is called service discovery.

3. **Health Checking**: The service registry can also monitor the health of registered services and remove or update entries when services become unavailable or change location.

Some popular service registries include:
- **Eureka** (by Netflix)
- **Consul** (by HashiCorp)
- **Zookeeper** (by Apache)

By using a service registry, you can achieve better scalability, load balancing, and fault tolerance in a distributed system.
