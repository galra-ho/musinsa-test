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
}