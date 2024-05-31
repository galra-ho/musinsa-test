package com.example.musinsa.application

import com.example.musinsa.common.ErrorCode
import com.example.musinsa.common.ImplementService
import com.example.musinsa.domain.entity.BrandEntity
import com.example.musinsa.infrastructure.BrandProvider
import org.springframework.transaction.annotation.Transactional

@ImplementService
class SetBrandService(
    private val brandProvider: BrandProvider
) {
    @Transactional
    fun addBrand(brand: BrandEntity?, brandName: String): BrandEntity {
        if (brand != null) {
            throw IllegalArgumentException(ErrorCode.ALREADY_BRAND.message)
        }

        return brandProvider.save(BrandEntity.from(brandName))
    }

    @Transactional
    fun update(findBrand: BrandEntity, brandName: String): BrandEntity {
        return findBrand.update(brandName)
    }

    @Transactional
    fun delete(findBrand: BrandEntity): BrandEntity {
        return findBrand.delete()
    }
}