package com.example.musinsa.presenter.response

import com.example.musinsa.domain.entity.ProductEntity
import java.math.BigDecimal
import java.time.LocalDateTime

data class AddProductResponse(
    val productId: Long,
    val brandName: String,
    val categoryCode: String,
    val price: BigDecimal,
    val createdAt: LocalDateTime
) {
    companion object {
        fun from(product: ProductEntity): AddProductResponse {
            return AddProductResponse(
                productId = product.id!!,
                brandName = product.brand.name,
                categoryCode = product.category.code.description,
                price = product.price.price,
                createdAt = product.createdAt
            )
        }
    }

}