package com.example.musinsa.presenter.response

import com.example.musinsa.domain.BrandProducts
import com.example.musinsa.domain.entity.CategoryEntity
import com.example.musinsa.domain.enums.CategoryCode
import java.math.BigDecimal

data class MinPriceBrandResponse(
    val brandName: String,
    val categories: List<MinPriceCategoryResponse>
) {
    companion object {
        fun of(
            brandProducts: BrandProducts,
            categoriesMap: Map<Long, CategoryEntity>
        ): MinPriceBrandResponse {
            return MinPriceBrandResponse(
                brandName = brandProducts.brandEntity.name,
                categories = brandProducts.products.productEntity.map {
                    MinPriceCategoryResponse(
                        code = categoriesMap[it.category.id]?.code!!,
                        price = it.price.getValue()
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
