package com.zhaops.productservice.product.repository;

import com.zhaops.productservice.product.entity.ProductComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author SoYuan
 */
public interface ProductCommentRepository extends JpaRepository<ProductComment, Long> {
    /**
     * 获取上了列表
     * @param productId 11
     * @return 111
     */
    List<ProductComment> findByProductIdOrderByCreated(Long productId);
}
