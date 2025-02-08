# Overview

Here’s an **overview** of this microservices system:  

---

### 📌 **Microservices Architecture for Order & Product Services**  

This system is built using **Spring Boot & Spring Cloud**, designed for scalability, flexibility, and maintainability. It follows a **distributed microservices** approach where each service is independent but communicates via well-defined APIs.

#### **🔹 Key Microservices:**
1. **Product Service** – Manages products, inventory, and details.  
2. **Order Service** – Handles order creation, tracking, and management.  

#### **🔹 Supporting Components:**
- **Spring Cloud Config Server** – Centralized configuration management using Git.  
- **Eureka Discovery Server** – Service registry for dynamic service discovery.  
- **Spring Cloud Gateway** – API Gateway for routing and security.  
- **Spring Boot Actuator** – Monitoring and health checks.  
- **Database** – Each microservice has its own **dedicated database** to ensure loose coupling.

#### **🔹 Flow of Requests:**
1. **User Request → API Gateway** (Routes requests to the appropriate microservice).  
2. **Microservice fetches configs** from Config Server.  
3. **Microservice discovers peers** via Eureka.  
4. **Database operations** for persistence.  

This architecture ensures **scalability, resilience, and fault tolerance** while enabling services to evolve independently.

---

***Typing...***

You, safe ✅ 
