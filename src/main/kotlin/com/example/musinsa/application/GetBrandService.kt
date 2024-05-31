package com.example.musinsa.application

import com.example.musinsa.common.ErrorCode
import com.example.musinsa.common.ImplementService
import com.example.musinsa.common.NotFoundException
import com.example.musinsa.domain.entity.BrandEntity
import com.example.musinsa.infrastructure.BrandProvider

@ImplementService
class GetBrandService(
    private val brandProvider: BrandProvider
) {
    fun getNullableByName(name: String): BrandEntity? {
        return brandProvider.findNullableByName(name)
    }

    fun getById(id: Long): BrandEntity {
        return brandProvider.findNullableById(id)
            ?: throw NotFoundException(ErrorCode.NOT_FOUND_BRAND)
    }

    fun getBrandMap(): Map<Long, BrandEntity> {
        return brandProvider.findAll().associateBy { it.id!! }
    }

    fun getAll(): List<BrandEntity> {
        return brandProvider.findAll()
    }

    fun getMapByBrandIds(brandIds: List<Long>): Map<Long, BrandEntity> {
        return brandProvider.findAllByIdIn(brandIds)
            .associateBy { it.id!! }
    }
}