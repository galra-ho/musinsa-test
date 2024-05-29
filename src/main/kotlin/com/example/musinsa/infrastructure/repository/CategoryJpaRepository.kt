package com.example.musinsa.infrastructure.repository

import com.example.musinsa.domain.entity.CategoryEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CategoryJpaRepository : JpaRepository<CategoryEntity, Long> {

}