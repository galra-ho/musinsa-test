package com.example.musinsa.infrastructure

import com.example.musinsa.domain.entity.CategoryEntity
import com.example.musinsa.domain.enums.CategoryCode
import com.example.musinsa.infrastructure.repository.CategoryJpaRepository
import org.springframework.stereotype.Component

@Component
class CategoryProvider(
    private val categoryJpaRepository: CategoryJpaRepository
) {

    fun findAll(): List<CategoryEntity> {
        return categoryJpaRepository.findAll()
    }

    fun findNullableByCategoryCode(category: CategoryCode): CategoryEntity? {
        return categoryJpaRepository.findByCode(category)
    }
}