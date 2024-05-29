package com.example.musinsa.presenter.response

import com.example.musinsa.application.dto.MinPriceBrand
import com.example.musinsa.domain.entity.CategoryEntity
import com.example.musinsa.domain.enums.CategoryCode
import java.math.BigDecimal

data class MinPriceBrandResponse(
    val brandName: String,
    val categories: List<MinPriceProductResponse>
) {
    companion object {
        fun from(minPriceBrand: MinPriceBrand, categoriesMap: Map<Long, CategoryEntity>): MinPriceBrandResponse {


            return MinPriceBrandResponse(
                brandName = minPriceBrand.brandEntity.name,
                categories = minPriceBrand.products.productEntity.map {
                    MinPriceProductResponse(
                        code = categoriesMap[it.category.id]?.code!!,
                        price = it.price
                    )
                }
            )
        }

    }
}

data class MinPriceProductResponse(
    val code: CategoryCode,
    val price: BigDecimal
)
