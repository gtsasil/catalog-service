package com.gtsasil.catalog_service.api.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gtsasil.catalog_service.api.dto.ProductCreateDTO;
import com.gtsasil.catalog_service.api.dto.ProductResponseDTO;
import com.gtsasil.catalog_service.domain.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<Page<ProductResponseDTO>> getProduct(
            @PageableDefault(size = 10, sort = "name", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(productService.findAll(pageable));
    }

    @PostMapping
    public ResponseEntity<ProductResponseDTO> postProduct(@RequestBody @Valid ProductCreateDTO dto) {

        return ResponseEntity.status(HttpStatus.CREATED).body(productService.create(dto));
    }
}
