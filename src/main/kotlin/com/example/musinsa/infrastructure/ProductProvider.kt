package com.example.musinsa.infrastructure

import com.example.musinsa.domain.Products
import com.example.musinsa.domain.entity.BrandEntity
import com.example.musinsa.domain.entity.CategoryEntity
import com.example.musinsa.domain.entity.ProductEntity
import com.example.musinsa.infrastructure.repository.ProductJpaRepository
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository

@Repository
class ProductProvider(
    private val productJpaRepository: ProductJpaRepository
) {
    fun findByCategory(category: CategoryEntity): Products {
        return Products(productJpaRepository.findAllByCategoryAndIsDeletedFalse(category))
    }

    fun findAllByCategoryAndBrand(brand: BrandEntity): Products {
        return Products(productJpaRepository.findAllByBrandAndIsDeletedFalse(brand))
    }

}