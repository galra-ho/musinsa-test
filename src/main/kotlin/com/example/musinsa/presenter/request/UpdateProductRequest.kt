package com.example.musinsa.presenter.request

import com.example.musinsa.domain.enums.CategoryCode
import java.math.BigDecimal

data class UpdateProductRequest(
    val updateCategoryCode: CategoryCode,
    val updateBrandId: Long,
    val updatePrice: BigDecimal
)