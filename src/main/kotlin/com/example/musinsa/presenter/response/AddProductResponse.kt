package com.example.musinsa.presenter.response

import com.example.musinsa.domain.entity.ProductEntity
import com.example.musinsa.domain.enums.CategoryCode
import java.math.BigDecimal
import java.time.LocalDateTime

data class AddProductResponse(
    val productId: Long,
    val brandName: String,
    val categoryCode: CategoryCode,
    val price: BigDecimal,
    val createdAt: LocalDateTime
) {
    companion object {
        fun from(product: ProductEntity): AddProductResponse {
            return AddProductResponse(
                productId = product.id!!,
                brandName = product.brand.name,
                categoryCode = product.category.code,
                price = product.price.getValue(),
                createdAt = product.createdAt
            )
        }
    }

}