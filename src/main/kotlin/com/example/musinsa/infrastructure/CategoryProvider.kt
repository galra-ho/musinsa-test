package com.example.musinsa.infrastructure

import com.example.musinsa.common.ImplementService
import com.example.musinsa.domain.entity.CategoryEntity
import com.example.musinsa.infrastructure.repository.CategoryJpaRepository
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository

@Repository
class CategoryProvider(
    private val categoryJpaRepository: CategoryJpaRepository
) {

    fun getAll(): List<CategoryEntity> {
        return categoryJpaRepository.findAll()
    }
}