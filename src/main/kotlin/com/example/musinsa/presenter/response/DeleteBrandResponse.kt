package com.example.musinsa.presenter.response

import com.example.musinsa.domain.entity.BrandEntity
import java.time.LocalDateTime

data class DeleteBrandResponse(
    val name: String,
    val updatedAt: LocalDateTime
) {
    companion object {
        fun from(brand: BrandEntity): DeleteBrandResponse {
            return DeleteBrandResponse(
                name = brand.name,
                updatedAt = brand.updatedAt
            )
        }
    }
}