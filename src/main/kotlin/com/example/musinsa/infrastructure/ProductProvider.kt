package com.example.musinsa.infrastructure

import com.example.musinsa.domain.ProductPrice
import com.example.musinsa.domain.Products
import com.example.musinsa.domain.entity.BrandEntity
import com.example.musinsa.domain.entity.CategoryEntity
import com.example.musinsa.domain.entity.ProductEntity
import com.example.musinsa.infrastructure.repository.ProductJpaRepository
import org.springframework.stereotype.Component

@Component
class ProductProvider(
    private val productJpaRepository: ProductJpaRepository
) {
    fun findByCategory(category: CategoryEntity): Products {
        return Products(productJpaRepository.findAllByCategoryAndIsDeletedFalse(category))
    }

    fun findAllBrand(brand: BrandEntity): Products {
        return Products(productJpaRepository.findAllByBrandAndIsDeletedFalse(brand))
    }

    fun save(product: ProductEntity): ProductEntity {
        return productJpaRepository.save(product)
    }

    fun findNullableById(id: Long): ProductEntity? {
        return productJpaRepository.findByIdAndIsDeletedFalse(id)
    }
}