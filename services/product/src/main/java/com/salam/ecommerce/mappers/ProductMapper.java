package com.salam.ecommerce.mappers;

import com.salam.ecommerce.models.Attachment;
import com.salam.ecommerce.models.Category;
import com.salam.ecommerce.dto.ProductPurchaseResponse;
import com.salam.ecommerce.dto.ProductRequest;
import com.salam.ecommerce.dto.ProductResponse;
import com.salam.ecommerce.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductMapper {

    public Product toProduct(String name, String description, String availableQuantity, String price, int category,
                             List<MultipartFile> images) {
        return Product.builder()
                .name(name)
                .description(description)
                .price(BigDecimal.valueOf(Long.parseLong(price)))
                .availableQuantity(Double.parseDouble(availableQuantity))
                .category(
                        Category.builder()
                                .id(category)
                                .build())
                .images(images.stream().map(
                        image -> Attachment.builder()
                        .fileName(images.get(0).getOriginalFilename())
                        .fileType(images.get(0).getContentType())
                        .build()).toList())
                .build();
    }

    public ProductResponse toProductResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getAvailableQuantity(),
                product.getPrice(),
                product.getCategory().getId(),
                product.getCategory().getName(),
                product.getCategory().getDescription(),
                product.getImages()
        );
    }

    public ProductPurchaseResponse toProductPurchaseResponse(Product product, double quantity) {
        return new ProductPurchaseResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                quantity
        );
    }
}
