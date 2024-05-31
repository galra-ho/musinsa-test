package com.example.musinsa.application.dto

import com.example.musinsa.domain.Products
import com.example.musinsa.domain.entity.ProductEntity

data class MinPriceAndMaxPriceProduct(
    val minPriceProduct: ProductEntity,
    val maxPriceProduct: ProductEntity
) {
    fun getBrandIds(): List<Long> {
        return listOf(minPriceProduct.id!!, maxPriceProduct.id!!)
    }

    companion object {
        fun from(products: Products): MinPriceAndMaxPriceProduct {
            return MinPriceAndMaxPriceProduct(
                minPriceProduct = products.getMinPriceProduct(),
                maxPriceProduct = products.getMaxPriceProduct()
            )
        }
    }
}