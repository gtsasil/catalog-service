package com.gtsasil.catalog_service.api.dto;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;

public record CategoryDTO(
        UUID id,
        @NotBlank String name,
        String description) {

}
