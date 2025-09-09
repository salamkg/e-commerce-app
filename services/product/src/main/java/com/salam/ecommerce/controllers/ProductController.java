package com.salam.ecommerce.controllers;

import com.salam.ecommerce.dto.ProductPurchaseRequest;
import com.salam.ecommerce.dto.ProductPurchaseResponse;
import com.salam.ecommerce.dto.ProductRequest;
import com.salam.ecommerce.dto.ProductResponse;
import com.salam.ecommerce.models.Attachment;
import com.salam.ecommerce.services.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping(value = "", consumes = "multipart/form-data")
    public ResponseEntity<Integer> createProduct(@RequestParam String name,
                                                 @RequestParam String description,
                                                 @RequestParam String availableQuantity,
                                                 @RequestParam String price,
                                                 @RequestParam int category,
                                                 @RequestParam List<MultipartFile> images) {
        return ResponseEntity.ok(productService.createProduct(name, description, availableQuantity, price, category, images));
    }

    @PostMapping("/purchase")
    public ResponseEntity<List<ProductPurchaseResponse>> purchaseProducts(@RequestBody List<ProductPurchaseRequest> requestList) {
        return ResponseEntity.ok(productService.purchaseProducts(requestList));
    }

    @GetMapping("{product-id}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable(name = "product-id") int productId) {
        return ResponseEntity.ok(productService.findById(productId));
    }

    @GetMapping()
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        return ResponseEntity.ok(productService.findAll());
    }
}
