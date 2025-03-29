# REST Controller

Create a controller class for the ProductService.

The controller class below seems to accept request on the path `/products/id?=<number>` to get the detail of the product with id of `number`.

```java
@RestController
@RequestMapping("/api")
public class ProductController {

    @GetMapping("/products/{id}")
    public ResponseEntity<String> getProduct(
        @PathVariable String id) {
            
            return ResponseEntity.ok("Product details for ID: " + id);
    }
}
```
