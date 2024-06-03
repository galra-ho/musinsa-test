package com.example.musinsa.application.dto

import com.example.musinsa.domain.Products
import com.example.musinsa.domain.entity.ProductEntity

data class MinAndMaxPriceProduct(
    val minPriceProduct: ProductEntity,
    val maxPriceProduct: ProductEntity
) {
    fun getBrandIds(): List<Long> {
        return listOf(minPriceProduct.id!!, maxPriceProduct.id!!)
    }

    companion object {
        fun from(products: Products): MinAndMaxPriceProduct {
            return MinAndMaxPriceProduct(
                minPriceProduct = products.getMinPriceProduct(),
                maxPriceProduct = products.getMaxPriceProduct()
            )
        }
    }
}