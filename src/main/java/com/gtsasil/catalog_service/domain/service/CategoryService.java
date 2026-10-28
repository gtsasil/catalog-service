package com.gtsasil.catalog_service.domain.service;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gtsasil.catalog_service.api.dto.CategoryDTO;
import com.gtsasil.catalog_service.domain.entity.Category;
import com.gtsasil.catalog_service.domain.repository.CategoryRepository;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "categories")
    public List<CategoryDTO> findAll() {
        return categoryRepository.findAll()
                .stream()
                .map(CategoryDTO::fromEntity)
                .toList();
    }

    @Transactional
    @CacheEvict(value = "categories", allEntries = true)
    public CategoryDTO create(CategoryDTO dto) {
        Category category = new Category();
        category.setName(dto.name());
        category.setDescription(dto.description());

        Category saved = categoryRepository.save(category);
        return CategoryDTO.fromEntity(saved);
    }

}