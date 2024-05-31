package com.example.musinsa.domain

import com.example.musinsa.common.ErrorCode.NOT_FOUND_PRICE
import com.example.musinsa.domain.entity.ProductEntity
import java.math.BigDecimal

@JvmInline
value class Products(
    val productEntity: List<ProductEntity>
) {
    fun getMinPriceByCategory(): Products {
        val result = this.productEntity
            .groupBy { it.category.id }
            .map { (_, product) ->
                getMinPriceProduct(product)
            }

        return Products(result)
    }

    fun calcTotalMinPrice(): BigDecimal {
        return this.productEntity
            .sumOf { it.price }
    }

    fun getMinPriceProduct(): ProductEntity {
        return getMinPriceProduct(productEntity)
    }

    private fun getMinPriceProduct(productEntity: List<ProductEntity>): ProductEntity {
        return productEntity
            .minByOrNull { it.price }
            ?: throw IllegalArgumentException(NOT_FOUND_PRICE.message)
    }

    fun getMaxPriceProduct(productEntity: List<ProductEntity>): ProductEntity =
        this.productEntity
            .maxByOrNull { it.price }
            ?: throw IllegalArgumentException(NOT_FOUND_PRICE.message)
}