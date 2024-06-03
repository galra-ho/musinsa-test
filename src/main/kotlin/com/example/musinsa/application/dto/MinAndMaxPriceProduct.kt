package com.example.musinsa.application.dto

import com.example.musinsa.domain.Products
import com.example.musinsa.domain.entity.ProductEntity

data class MinAndMaxPriceProductDto(
    val minPriceProduct: ProductEntity,
    val maxPriceProduct: ProductEntity
) {
    fun getBrandIds(): List<Long> {
        return listOf(minPriceProduct.brand.id!!, maxPriceProduct.brand.id!!)
    }

    companion object {
        fun from(products: Products): MinAndMaxPriceProductDto {
            return MinAndMaxPriceProductDto(
                minPriceProduct = products.getMinPriceProduct(),
                maxPriceProduct = products.getMaxPriceProduct()
            )
        }
    }
}