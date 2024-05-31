package com.example.musinsa.presenter.response

import com.example.musinsa.application.dto.MinPriceBrand
import com.example.musinsa.domain.entity.CategoryEntity
import com.example.musinsa.domain.enums.CategoryCode
import java.math.BigDecimal

data class MinPriceBrandResponse(
    val brandName: String,
    val categories: List<MinPriceCategoryResponse>
) {
    companion object {
        fun of(
            minPriceBrand: MinPriceBrand,
            categoriesMap: Map<Long, CategoryEntity>
        ): MinPriceBrandResponse {
            return MinPriceBrandResponse(
                brandName = minPriceBrand.brandEntity.name,
                categories = minPriceBrand.products.productEntity.map {
                    MinPriceCategoryResponse(
                        code = categoriesMap[it.category.id]?.code!!,
                        price = it.price.price
                    )
                }
            )
        }

    }
}

data class MinPriceCategoryResponse(
    val code: CategoryCode,
    val price: BigDecimal
)
