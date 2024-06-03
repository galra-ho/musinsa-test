package com.example.musinsa.domain

import com.example.musinsa.common.ErrorCode
import com.example.musinsa.common.NotFoundException
import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.math.BigDecimal

@Embeddable
data class ProductPrice(
    @Column(name = "price")
    private val value: BigDecimal
) {
    init {
        if (value <= BigDecimal.ZERO) {
            throw NotFoundException(ErrorCode.PRICE_IS_NOT_ZERO)
        }
    }

    fun getValue(): BigDecimal = this.value
}