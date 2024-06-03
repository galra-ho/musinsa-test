package com.example.musinsa.presenter.response

import com.example.musinsa.domain.entity.ProductEntity
import java.math.BigDecimal
import java.time.LocalDateTime

data class UpdateProductResponse(
    val brandName: String,
    val categoryCode: String,
    val price: BigDecimal,
    val updatedAt: LocalDateTime
) {
    companion object {
        fun from(product: ProductEntity): UpdateProductResponse {
            return UpdateProductResponse(
                brandName = product.brand.name,
                categoryCode = product.category.code.description,
                price = product.price.getValue(),
                updatedAt = product.updatedAt
            )
        }
    }
}