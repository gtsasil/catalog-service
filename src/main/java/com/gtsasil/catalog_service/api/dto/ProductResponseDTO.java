package com.gtsasil.catalog_service.api.dto;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;

public record ProductResponseDTO(
        UUID id,
        @NotBlank String name,
        BigDecimal price,
        Integer stockQuantity,
        UUID categoryId,
        String categoryName) {

}
