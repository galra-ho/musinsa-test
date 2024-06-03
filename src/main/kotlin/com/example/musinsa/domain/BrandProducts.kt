package com.example.musinsa.domain

import com.example.musinsa.domain.entity.BrandEntity
import java.math.BigDecimal

data class BrandProducts(
    val brandEntity: BrandEntity,
    val products: Products
) {
    companion object {
        fun of(products: Products, brand: BrandEntity): BrandProducts {
            return BrandProducts(
                brandEntity = brand,
                products = products
            )
        }
    }
}

fun List<BrandProducts>.findMinPriceProductOfTheBrand(): BrandProducts {
    return this
        .associateBy { it.brandEntity.id!! }
        .minBy { it.value.products.sumTotalMinPrice() }
        .value
}