package com.example.musinsa.domain

import com.example.musinsa.domain.entity.CategoryEntity
import com.example.musinsa.domain.entity.ProductEntity
import java.math.BigDecimal

@JvmInline
value class Products(
    val productEntity: List<ProductEntity>
) {
    fun getMinPriceByCategory(): Products {
        val result = this.productEntity
            .groupBy { it.category.id }
            .map { ( _, product) -> product.minBy { it.price } }

        return Products(result)
    }

    fun calcTotalMinPrice(): BigDecimal {
        return this.productEntity
            .sumOf { it.price }
    }

}