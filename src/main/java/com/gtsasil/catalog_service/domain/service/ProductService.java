package com.gtsasil.catalog_service.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.gtsasil.catalog_service.api.dto.ProductCreateDTO;
import com.gtsasil.catalog_service.api.dto.ProductResponseDTO;
import com.gtsasil.catalog_service.domain.entity.Category;
import com.gtsasil.catalog_service.domain.entity.Product;
import com.gtsasil.catalog_service.domain.repository.CategoryRepository;
import com.gtsasil.catalog_service.domain.repository.ProductRepository;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

	private final ProductRepository productRepository;
	private final CategoryRepository categoryRepository;

	public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
		this.productRepository = productRepository;
		this.categoryRepository = categoryRepository;
	}

	@Transactional(readOnly = true)
	public Page<ProductResponseDTO> findAll(Pageable pageable) {
		return productRepository.findAllWithCategory(pageable)
				.map(ProductResponseDTO::fromEntity);
	}

	@Transactional
	public ProductResponseDTO create(ProductCreateDTO dto) {
		Category category = categoryRepository.findById(dto.categoryId())
				.orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada com ID: " + dto.categoryId()));

		Product product = new Product();
		product.setName(dto.name());
		product.setDescription(dto.description());
		product.setPrice(dto.price());
		product.setStockQuantity(dto.stockQuantity());
		product.setCategory(category);

		Product saved = productRepository.save(product);
		return ProductResponseDTO.fromEntity(saved);
	}
}
