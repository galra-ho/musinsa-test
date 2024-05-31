package com.example.musinsa.infrastructure

import com.example.musinsa.domain.entity.BrandEntity
import com.example.musinsa.infrastructure.repository.BrandJpaRepository
import org.springframework.stereotype.Repository

@Repository
class BrandProvider(
    private val brandJpaRepository: BrandJpaRepository
) {
    fun findAll(): List<BrandEntity> {
        return brandJpaRepository.findAll()
    }

    fun findAllByIdIn(brandIds: List<Long>): List<BrandEntity> {
        return brandJpaRepository.findAllByIdIn(brandIds)
    }
}