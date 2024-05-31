package com.example.musinsa.presenter.response

import com.example.musinsa.domain.entity.ProductEntity
import java.time.LocalDateTime

data class DeleteProductResponse(
    val productId: Long,
    val updatedAt: LocalDateTime
) {
    companion object {
        fun from(product: ProductEntity): DeleteProductResponse {
            return DeleteProductResponse(
                productId = product.id!!,
                updatedAt = product.updatedAt
            )
        }
    }
}