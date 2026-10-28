package com.gtsasil.catalog_service.api.dto;

import java.util.UUID;

import com.gtsasil.catalog_service.domain.entity.Category;

import jakarta.validation.constraints.NotBlank;

public record CategoryDTO(
        UUID id,
        @NotBlank String name,
        String description) {

    public static CategoryDTO fromEntity(Category category) {
        return new CategoryDTO(
                category.getId(),
                category.getName(),
                category.getDescription()
        );
    }

}
