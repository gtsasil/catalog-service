package com.gtsasil.catalog_service.api.dto;

import java.math.BigDecimal;
import java.util.UUID;

import com.gtsasil.catalog_service.domain.entity.Product;

public record ProductResponseDTO(
        UUID id,
        String name,
        String description,
        BigDecimal price,
        Integer stockQuantity,
        UUID categoryId,
        String categoryName) {

    public static ProductResponseDTO fromEntity(Product product) {
        return new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStockQuantity(),
                product.getCategory().getId(),
                product.getCategory().getName()
        );
    }

}
