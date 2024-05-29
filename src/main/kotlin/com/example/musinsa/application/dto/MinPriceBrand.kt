package com.example.musinsa.application.dto

import com.example.musinsa.domain.Products
import com.example.musinsa.domain.entity.BrandEntity
import java.math.BigDecimal

data class MinPriceBrand(
    val minPrice: BigDecimal,
    val brandEntity: BrandEntity,
    val products: Products
) {
    companion object {
        fun of(products: Products, brandEntity: BrandEntity): MinPriceBrand {
            return MinPriceBrand(
                minPrice = products.calcTotalMinPrice(),
                brandEntity = brandEntity,
                products = products
            )
        }
    }
}