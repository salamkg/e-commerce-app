package com.salam.ecommerce.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    public Integer createProduct(ProductRequest productRequest) {
        var product = productRepository.save(productMapper.toProduct(productRequest));
        return product.getId();
    }

    public List<ProductResponse> purchaseProducts(List<ProductPurchaseRequest> requestList) {
        return null;
    }
}
