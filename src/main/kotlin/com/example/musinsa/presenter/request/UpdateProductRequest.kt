package com.example.musinsa.presenter.request

import com.example.musinsa.domain.ProductPrice
import com.example.musinsa.domain.enums.CategoryCode

data class UpdateProductRequest(
    val updateCategoryCode: CategoryCode,
    val updateBrandId: Long,
    val updatePrice: ProductPrice
)