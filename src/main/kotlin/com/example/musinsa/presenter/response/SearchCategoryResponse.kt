package com.example.musinsa.presenter.response

import com.example.musinsa.domain.entity.BrandEntity
import com.example.musinsa.domain.entity.CategoryEntity
import com.example.musinsa.domain.entity.ProductEntity
import com.example.musinsa.domain.enums.CategoryCode
import java.math.BigDecimal

data class SearchCategoryResponse(
    val category: String,
    val minPriceBrand: List<SearchBrandProductResponse>,
    val maxPriceBrand: List<SearchBrandProductResponse>
) {
    companion object {
        fun of(
            category: CategoryEntity,
            minPriceBrand: BrandEntity?,
            maxPriceBrand: BrandEntity?,
            maxPriceProduct: ProductEntity,
            minPriceProduct: ProductEntity
        ): SearchCategoryResponse {
            val maxPriceProductResponse =
                SearchBrandProductResponse(
                    brandName = maxPriceBrand?.name ?: throw IllegalArgumentException(),
                    price = maxPriceProduct.price
                )

            val minPriceProductResponse =
                SearchBrandProductResponse(
                    brandName = minPriceBrand?.name ?: throw IllegalArgumentException(),
                    price = minPriceProduct.price
                )

            return SearchCategoryResponse(
                category = category.code.description,
                maxPriceBrand = listOf(maxPriceProductResponse),
                minPriceBrand = listOf(minPriceProductResponse)
            )
        }
    }
}

data class SearchBrandProductResponse(
    val brandName: String,
    val price: BigDecimal
)
