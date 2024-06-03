package com.example.musinsa.presenter

import com.example.musinsa.domain.enums.CategoryCode
import com.example.musinsa.support.IntegrationTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.math.BigDecimal

@IntegrationTest
class RankControllerTest(
    private val rankController: RankController
) {

    @Test
    fun `카테고리 별 최저가격 브랜드와 상품 가격 총액을 조회한다`() {

        val result = rankController.getMinPriceByCategory()
            .body

        assertThat(result).isNotNull
        assertThat(result?.totalPrice).isEqualByComparingTo(BigDecimal(34100))
        assertThat(result?.minPriceProductInfo?.get(0)?.price).isEqualByComparingTo(BigDecimal(10000))
        assertThat(result?.minPriceProductInfo?.get(0)?.brandName).isEqualTo("C")
        assertThat(result?.minPriceProductInfo?.get(0)?.categoryCode).isEqualTo(CategoryCode.TOP)

        assertThat(result?.minPriceProductInfo?.get(1)?.price).isEqualByComparingTo(BigDecimal(5000))
        assertThat(result?.minPriceProductInfo?.get(1)?.brandName).isEqualTo("E")
        assertThat(result?.minPriceProductInfo?.get(1)?.categoryCode).isEqualTo(CategoryCode.OUTER)

        assertThat(result?.minPriceProductInfo?.get(2)?.price).isEqualByComparingTo(BigDecimal(3000))
        assertThat(result?.minPriceProductInfo?.get(2)?.brandName).isEqualTo("D")
        assertThat(result?.minPriceProductInfo?.get(2)?.categoryCode).isEqualTo(CategoryCode.PANTS)

        assertThat(result?.minPriceProductInfo?.get(3)?.price).isEqualByComparingTo(BigDecimal(9000))
        assertThat(result?.minPriceProductInfo?.get(3)?.brandName).isEqualTo("A")
        assertThat(result?.minPriceProductInfo?.get(3)?.categoryCode).isEqualTo(CategoryCode.SNEAKERS)

        assertThat(result?.minPriceProductInfo?.get(4)?.price).isEqualByComparingTo(BigDecimal(2000))
        assertThat(result?.minPriceProductInfo?.get(4)?.brandName).isEqualTo("A")
        assertThat(result?.minPriceProductInfo?.get(4)?.categoryCode).isEqualTo(CategoryCode.BAG)

        assertThat(result?.minPriceProductInfo?.get(5)?.price).isEqualByComparingTo(BigDecimal(1500))
        assertThat(result?.minPriceProductInfo?.get(5)?.brandName).isEqualTo("D")
        assertThat(result?.minPriceProductInfo?.get(5)?.categoryCode).isEqualTo(CategoryCode.HAT)

        assertThat(result?.minPriceProductInfo?.get(6)?.price).isEqualByComparingTo(BigDecimal(1700))
        assertThat(result?.minPriceProductInfo?.get(6)?.brandName).isEqualTo("I")
        assertThat(result?.minPriceProductInfo?.get(6)?.categoryCode).isEqualTo(CategoryCode.SOCKS)

        assertThat(result?.minPriceProductInfo?.get(7)?.price).isEqualByComparingTo(BigDecimal(1900))
        assertThat(result?.minPriceProductInfo?.get(7)?.brandName).isEqualTo("F")
        assertThat(result?.minPriceProductInfo?.get(7)?.categoryCode).isEqualTo(CategoryCode.ACCESSORY)
    }

    @Test
    fun `카테고리 이름으로 최저, 최고 가격 브랜드와 상품 가격을 조회한다`() {
        val result = rankController.searchMinAndMaxPriceBrandByCategory(
            category = CategoryCode.OUTER
        ).body


        assertThat(result).isNotNull
        assertThat(result?.category).isEqualTo(CategoryCode.OUTER)

        assertThat(result?.minPriceBrand?.get(0)?.price).isEqualByComparingTo(BigDecimal(5000))
        assertThat(result?.minPriceBrand?.get(0)?.brandName).isEqualTo("E")

        assertThat(result?.maxPriceBrand?.get(0)?.price).isEqualByComparingTo(BigDecimal(7200))
        assertThat(result?.maxPriceBrand?.get(0)?.brandName).isEqualTo("F")
    }
}