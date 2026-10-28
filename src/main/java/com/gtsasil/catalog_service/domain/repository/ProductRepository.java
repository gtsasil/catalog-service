package com.gtsasil.catalog_service.domain.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gtsasil.catalog_service.domain.entity.Product;

public interface ProductRepository extends JpaRepository<Product, UUID> {

    @Query(value = "SELECT p FROM Product p JOIN FETCH p.category", countQuery = "SELECT count(p) FROM Product p")
    Page<Product> findAllWithCategory(Pageable pageable);

    Page<Product> findByCategoryId(UUID categoryId, Pageable pageable);
}
