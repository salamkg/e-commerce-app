package com.salam.ecommerce.services;

import com.salam.ecommerce.dto.ProductPurchaseRequest;
import com.salam.ecommerce.dto.ProductPurchaseResponse;
import com.salam.ecommerce.dto.ProductRequest;
import com.salam.ecommerce.dto.ProductResponse;
import com.salam.ecommerce.mappers.ProductMapper;
import com.salam.ecommerce.exceptions.ProductPurchaseException;
import com.salam.ecommerce.models.Attachment;
import com.salam.ecommerce.models.Category;
import com.salam.ecommerce.models.Product;
import com.salam.ecommerce.repositories.AttachmentRepository;
import com.salam.ecommerce.repositories.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor()
public class ProductService {

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;
    private final AttachmentRepository attachmentRepository;

    public Integer createProduct(String name, String description, String availableQuantity, String price, int category,
                                 List<MultipartFile> images) {
        Product product = Product.builder()
                .name(name)
                .description(description)
                .availableQuantity(Double.parseDouble(availableQuantity))
                .price(BigDecimal.valueOf(Long.parseLong(price)))
                .category(
                        Category.builder()
                                .id(category)
                                .build())
                .build();
        productRepository.save(product);

        List<Attachment> imageList = images.stream().map(
                image -> Attachment.builder()
                        .fileName(images.get(0).getOriginalFilename())
                        .fileType(images.get(0).getContentType())
                        .product(product)
                        .build()
        ).toList();
        attachmentRepository.saveAll(imageList);

        return product.getId();
    }

    public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> requestList) {
        var productIds = requestList
                .stream()
                .map(ProductPurchaseRequest::productId)
                .toList();
        var storedProducts = productRepository.findAllByIdIn((productIds));

        if (productIds.size() != storedProducts.size()) {
            throw new ProductPurchaseException("One or more products does not exist");
        }
        var storesRequest = requestList
                .stream()
                .sorted(Comparator.comparing(ProductPurchaseRequest::productId))
                .toList();
        var purchasedProducts = new ArrayList<ProductPurchaseResponse>();

        for (int i = 0; i < storedProducts.size(); i++) {
            var product = storedProducts.get(i);
            var productRequest = storesRequest.get(i);

            if (product.getAvailableQuantity() < productRequest.quantity()) {
                throw new ProductPurchaseException("Insufficient quantity for product with id " + productRequest.productId());
            }

            var newAvailableQuantity = product.getAvailableQuantity() - productRequest.quantity();
            product.setAvailableQuantity(newAvailableQuantity);
            productRepository.save(product);
            purchasedProducts.add(productMapper.toProductPurchaseResponse(product, productRequest.quantity()));
        }
        return purchasedProducts;
    }

    public ProductResponse findById(int productId) {
        return productRepository.findById(productId)
                .map(productMapper::toProductResponse)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with the id:: " + productId));
    }

    public List<ProductResponse> findAll() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toProductResponse)
                .collect(Collectors.toList());
    }
}
