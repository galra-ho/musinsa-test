package com.example.musinsa.domain

import com.example.musinsa.common.ErrorCode
import com.example.musinsa.support.UnitTest
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import java.math.BigDecimal

@UnitTest
class ProductPriceTest {

    @Test
    fun `상품의 가격은 0원일 경우 오류가 발생한다`() {

        Assertions.assertThatThrownBy {
            ProductPrice(BigDecimal.ZERO)
        }.hasMessage(ErrorCode.PRICE_IS_NOT_ZERO.message)

    }

    @Test
    fun `상품의 가격은 0원 보다 작을 경우 오류가 발생한다`() {

        Assertions.assertThatThrownBy {
            ProductPrice(BigDecimal(-100))
        }.hasMessage(ErrorCode.PRICE_IS_NOT_ZERO.message)
    }
}