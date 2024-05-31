package com.example.musinsa.application

import com.example.musinsa.common.ErrorCode
import com.example.musinsa.common.ImplementService
import com.example.musinsa.common.NotFoundException
import com.example.musinsa.domain.entity.ProductEntity
import com.example.musinsa.infrastructure.ProductProvider

@ImplementService
class GetProductService(
    private val productProvider: ProductProvider
) {
    fun getById(id: Long): ProductEntity {
        return productProvider.findNullableById(id)
            ?: throw NotFoundException(ErrorCode.NOT_FOUND_PRODUCT)
    }
}