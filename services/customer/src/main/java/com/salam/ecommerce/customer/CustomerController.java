package com.salam.ecommerce.customer;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private CusromerService cusromerService;

    @PostMapping
    public ResponseEntity<String> createCustomer(
            @RequestBody CustomerRequest customerRequest
    ) {
        return ResponseEntity.ok(cusromerService.createCustomer(customerRequest));
    }

    @PutMapping
    public ResponseEntity<String> updateCustomer(@RequestBody CustomerRequest customerRequest) {
        cusromerService.updateCustomer(customerRequest);
        return ResponseEntity.accepted().build();
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> findAll() {
        return ResponseEntity.ok(cusromerService.findAllCustomers());
    }

    @GetMapping("/exists/{customerId}")
    public ResponseEntity<Boolean> existsById(@PathVariable("customer-id") String customerId) {
        return ResponseEntity.ok(cusromerService.existsById(customerId));
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerResponse> findById(@PathVariable("customer-id") String customerId) {
        return ResponseEntity.ok(cusromerService.findById(customerId));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteCustomer(@PathVariable("customer-id") String customerId) {
        cusromerService.deleteCustomer(customerId);
        return ResponseEntity.accepted().build();
    }
}
