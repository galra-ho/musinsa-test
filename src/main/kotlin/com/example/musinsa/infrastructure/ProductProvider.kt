package com.example.musinsa.infrastructure

import com.example.musinsa.common.ImplementService
import com.example.musinsa.domain.entity.ProductEntity
import com.example.musinsa.domain.enums.CategoryCode
import com.example.musinsa.infrastructure.repository.ProductJpaRepository
import org.springframework.stereotype.Component

@Component
class ProductProvider(
    private val productJpaRepository: ProductJpaRepository
) {
    fun findByCategory(category: CategoryCode): List<ProductEntity> {
        return productJpaRepository.findAllByCategoryAndIsDeletedFalse(category)
    }

}