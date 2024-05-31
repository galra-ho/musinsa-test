package com.example.musinsa.infrastructure.repository

import com.example.musinsa.domain.entity.BrandEntity
import com.example.musinsa.domain.entity.CategoryEntity
import com.example.musinsa.domain.entity.ProductEntity
import com.example.musinsa.domain.enums.CategoryCode
import org.springframework.data.jpa.repository.JpaRepository

interface ProductJpaRepository : JpaRepository<ProductEntity, Long> {
    fun findAllByCategoryAndIsDeletedFalse(code: CategoryEntity): List<ProductEntity>

    fun findAllByBrandAndIsDeletedFalse(brand: BrandEntity): List<ProductEntity>
}