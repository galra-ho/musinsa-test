package com.example.musinsa.infrastructure

import com.example.musinsa.common.ImplementService
import com.example.musinsa.domain.entity.BrandEntity
import com.example.musinsa.infrastructure.repository.BrandJpaRepository
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository

@Repository
class BrandProvider(
    private val brandJpaRepository: BrandJpaRepository
) {
    fun getAll(): List<BrandEntity> {
        return brandJpaRepository.findAll()
    }
}