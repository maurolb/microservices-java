package mauro.shopping.shopping.clients;

import mauro.shopping.shopping.models.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "product-service")
public interface ProductClient {
    @GetMapping(value = "/products/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id);

    @GetMapping(value = "/products/{id}/stock")
    public ResponseEntity<Product> updateStock(@PathVariable Long id,@RequestParam(value = "quantity", required = true) Double quantity);
}