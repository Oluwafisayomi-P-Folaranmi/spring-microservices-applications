# Test the System

Here we will test the system of microservices.

## Test Eureka Server

### **1. Start Eureka Server**

1. **Ensure Eureka Server is running** by checking its logs.  
2. Open **`http://localhost:8761`** in a browser.  
3. If Eureka is running, you should see the Eureka dashboard.

### **2. Check if Microservices Registered Successfully**
- In the Eureka dashboard, **your microservice (product-service) should appear** under "Instances currently registered with Eureka."  
- If it doesn’t appear:
  - Check if your service has `eureka.client.service-url.defaultZone=http://localhost:8761/eureka`.
  - Restart the service.

### **3. Verify Eureka Logs**
Run the following **cURL command** to check registered instances:  

```sh
curl -X GET http://localhost:8761/eureka/apps
```

If Eureka is working, you’ll see JSON/XML data listing registered services.

## Test Spring Cloud Config Server

### **1. Start Config Server**

1. Make sure it starts without errors.
2. **Ensure the Config Server is running** by checking its logs.

### **2. Check Config Properties**  

Spring Cloud Config exposes endpoints to check configuration properties. You can test this by making a request to **`http://localhost:8888/{application-name}/{profile}`**. For example, **`http://localhost:8888/product-service/default`**. If the **Config Server is working**, you should see a JSON response with configuration properties.

Example response:
```json
{
  "name": "product-service",
  "profiles": ["default"],
  "propertySources": [
    {
      "source": {
        "spring.datasource.url": "jdbc:mysql://localhost:3306/productdb",
        "spring.datasource.username": "root",
        "spring.datasource.password": "password"
      }
    }
  ]
}
```

If you see an **error** instead, check:
- The `spring.cloud.config.server.git.uri` in `Config Server`.
- Whether the repository exists with the correct `product-service.properties` or `product-service.yml`.

### **3. Verify Config Client Logs**

In your microservice logs, you should see messages like:  
✅ **"Fetched config from http://localhost:8888"**  
✅ **"Connected to Config Server"**

Now. We have been able to set up and test our apps up to this level. Kudos ✅
