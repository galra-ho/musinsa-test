package com.example.musinsa.presenter.response

import com.example.musinsa.domain.entity.BrandEntity
import com.example.musinsa.domain.entity.CategoryEntity
import com.example.musinsa.domain.entity.ProductEntity
import com.example.musinsa.domain.enums.CategoryCode
import java.math.BigDecimal

data class MinPriceByCategoryResponse(
    val minPriceProductInfo: List<MinPriceProductResponse>,
    val totalPrice: BigDecimal
) {
    companion object { 
        fun from(
            response: List<MinPriceProductResponse>
        ): MinPriceByCategoryResponse =
            MinPriceByCategoryResponse(
                minPriceProductInfo = response,
                totalPrice = response.sumOf { it.price }
            )
    }
}

data class MinPriceProductResponse(
    val categoryCode: CategoryCode,
    val brandName: String,
    val price: BigDecimal
) {
    companion object {
        fun of(
            product: ProductEntity,
            brand: BrandEntity,
            categoryEntity: CategoryEntity
        ): MinPriceProductResponse =
            MinPriceProductResponse(
                categoryCode = categoryEntity.code,
                brandName = brand.name,
                price = product.price.price
            )
    }
}