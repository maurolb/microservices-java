package mauro.shopping.shopping.clients;

import mauro.shopping.shopping.models.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customer-service")
public interface CustomerClient {
    @GetMapping(value = "/customers/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id);
}
