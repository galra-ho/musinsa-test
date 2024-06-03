package com.example.musinsa.domain

import com.example.musinsa.support.fixture.Brand
import com.example.musinsa.support.fixture.Product
import com.example.musinsa.support.fixture.makeBrand
import com.example.musinsa.support.fixture.makeProduct
import com.example.musinsa.support.UnitTest
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

@UnitTest
class BrandProductsTest {

    @Test
    fun `브랜드 구성 상품들의 총합이 가장 작은 브랜드를 리턴한다`() {
        val 캉골 = makeBrand(Brand.캉골)
        val 노스페이스 = makeBrand(Brand.노스페이스)
        val 캉골_상의 = makeProduct(Product.캉골_상의, Brand.캉골)
        val 캉골_패딩 = makeProduct(Product.캉골_패딩, Brand.캉골)
        val 노스페이스_상의 = makeProduct(Product.노스페이스_상의, Brand.노스페이스)
        val 노스페이스_패딩 = makeProduct(Product.노스페이스_패딩, Brand.노스페이스)

        val result = listOf(
            BrandProducts(
                brandEntity = 캉골,
                products = Products(listOf(캉골_상의, 캉골_패딩))
            ),

            BrandProducts(
                brandEntity = 노스페이스,
                products = Products(listOf(노스페이스_상의, 노스페이스_패딩))
            )
        ).findMinPriceProductOfTheBrand()

        assertThat(result.brandEntity.id).isEqualTo(캉골.id)
    }
}