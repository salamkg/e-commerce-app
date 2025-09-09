package com.salam.ecommerce.dto;

import com.salam.ecommerce.models.Attachment;

import java.math.BigDecimal;
import java.util.List;

public record ProductResponse(
        int id,
        String name,
        String description,
        double availableQuantity,
        BigDecimal price,
        int categoryId,
        String categoryName,
        String categoryDescription,
        List<Attachment> images
) {
}
