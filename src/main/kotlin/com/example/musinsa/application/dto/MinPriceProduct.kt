package com.example.musinsa.application.dto

import com.example.musinsa.domain.entity.BrandEntity
import com.example.musinsa.domain.entity.CategoryEntity
import com.example.musinsa.domain.entity.ProductEntity
import com.example.musinsa.domain.enums.CategoryCode
import java.math.BigDecimal

data class MinPriceProduct(
    val minPrice: BigDecimal,
    val category: CategoryEntity,
    val brand: BrandEntity,
    val product: ProductEntity
)