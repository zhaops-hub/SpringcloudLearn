package com.zhaops.productservice.product.repository;

import com.zhaops.productservice.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author SoYuan
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
}
