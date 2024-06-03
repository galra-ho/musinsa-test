package com.example.musinsa.presenter.response

import com.example.musinsa.common.ErrorCode
import com.example.musinsa.common.NotFoundException
import com.example.musinsa.domain.ProductPrice
import com.example.musinsa.domain.entity.BrandEntity
import com.example.musinsa.domain.entity.CategoryEntity
import com.example.musinsa.domain.entity.ProductEntity
import com.example.musinsa.domain.enums.CategoryCode

data class SearchCategoryResponse(
    val category: CategoryCode,
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
                    brandName = maxPriceBrand?.name ?: throw NotFoundException(ErrorCode.NOT_FOUND_BRAND),
                    price = maxPriceProduct.price
                )

            val minPriceProductResponse =
                SearchBrandProductResponse(
                    brandName = minPriceBrand?.name ?: throw NotFoundException(ErrorCode.NOT_FOUND_BRAND),
                    price = minPriceProduct.price
                )

            return SearchCategoryResponse(
                category = category.code,
                maxPriceBrand = listOf(maxPriceProductResponse),
                minPriceBrand = listOf(minPriceProductResponse)
            )
        }
    }
}

data class SearchBrandProductResponse(
    val brandName: String,
    val price: ProductPrice
)
