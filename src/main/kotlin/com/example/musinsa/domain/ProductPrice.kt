package com.example.musinsa.domain

import com.example.musinsa.common.ErrorCode
import java.math.BigDecimal

@JvmInline
value class ProductPrice(
    val price: BigDecimal
) {
    init {
        if (price <= BigDecimal.ZERO) {
            throw IllegalArgumentException(ErrorCode.PRICE_IS_NOT_ZERO.message)
        }
    }
}