package com.example.musinsa.infrastructure

import com.example.musinsa.common.ErrorCode
import com.example.musinsa.common.NotFoundException
import com.example.musinsa.domain.entity.BrandEntity
import com.example.musinsa.infrastructure.repository.BrandJpaRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class BrandProvider(
    private val brandJpaRepository: BrandJpaRepository
) {
    fun findAll(): List<BrandEntity> {
        return brandJpaRepository.findAll()
    }

    fun findAllByIdIn(brandIds: List<Long>): List<BrandEntity> {
        return brandJpaRepository.findAllByIdIn(brandIds)
    }

    fun findNullableByName(brandName: String): BrandEntity? {
        return brandJpaRepository.findByNameAndIsDeletedFalse(brandName)
    }

    fun save(brand: BrandEntity): BrandEntity {
        return brandJpaRepository.save(brand)
    }

    fun findNullableById(id: Long): BrandEntity? {
        return brandJpaRepository.findByIdAndIsDeletedFalse(id)
    }
}