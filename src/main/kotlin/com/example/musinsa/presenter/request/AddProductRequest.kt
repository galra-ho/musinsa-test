package com.example.musinsa.presenter.request

import com.example.musinsa.domain.enums.CategoryCode
import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

data class AddProductRequest(
    @JsonProperty("brandId")
    val brandId: Long,

    @JsonProperty("categoryCode")
    val categoryCode: CategoryCode,

    @JsonProperty("price")
    val price: BigDecimal
)