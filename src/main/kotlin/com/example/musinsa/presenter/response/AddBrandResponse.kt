package com.example.musinsa.presenter.response

import com.example.musinsa.domain.entity.BrandEntity
import java.time.LocalDateTime

data class AddBrandResponse(
    val brandId: Long,
    val name: String,
    val createdAt: LocalDateTime
) {
    companion object {
        fun from(brand: BrandEntity): AddBrandResponse =
            AddBrandResponse(
                name = brand.name,
                createdAt = brand.createdAt,
                brandId = brand.id!!
            )
    }
}