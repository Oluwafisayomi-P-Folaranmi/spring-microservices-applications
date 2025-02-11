# Test System

Here, we will test the microservices system.

1. **Start Eureka Server**
2. **Start Product Service** (Port **8081**)
3. **Start Order Service** (Port **8082**)  
4. **Check the Eureka dashboard** on `http://localhost:8761`.
5. In the Eureka dashboard, **your microservice (product-service) should appear** under "Instances currently registered with Eureka."
6. **Test your endpoints**, by making a call to `OrderService` API:  
   ```
   GET http://localhost:8082/orders/1
   ```
   + If **`ProductService` is running**, it will return product details.  
   + If **`ProductService` is down**, it will return a fallback message.
