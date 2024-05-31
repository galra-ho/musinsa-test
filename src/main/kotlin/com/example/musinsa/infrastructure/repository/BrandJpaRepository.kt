package com.example.musinsa.infrastructure.repository

import com.example.musinsa.domain.entity.BrandEntity
import org.springframework.data.jpa.repository.JpaRepository

interface BrandJpaRepository : JpaRepository<BrandEntity, Long> {
    fun findAllByIdIn(brandIds: List<Long>): List<BrandEntity>
    fun findByNameAndIsDeletedFalse(name: String): BrandEntity?

    fun findByIdAndIsDeletedFalse(id: Long): BrandEntity?

}