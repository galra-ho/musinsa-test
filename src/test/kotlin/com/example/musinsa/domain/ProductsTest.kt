package com.example.musinsa.domain

import com.example.musinsa.support.fixture.Brand
import com.example.musinsa.support.fixture.Product
import com.example.musinsa.support.fixture.makeProduct
import com.example.musinsa.support.UnitTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.math.BigDecimal

@UnitTest
class ProductsTest {
    @Test
    fun `카테고리별 최소가격상품 을 리턴한다`() {
        val 나이키_상의 = makeProduct(Product.나이키_상의, Brand.나이키)
        val 나이키_양말 = makeProduct(Product.나이키_양말, Brand.나이키)
        val 나이키_신발 = makeProduct(Product.나이키_신발, Brand.나이키)
        val 아디다스_양말 = makeProduct(Product.아디다스_양말, Brand.아디다스)
        val 아디다스_신발 = makeProduct(Product.아디다스_신발, Brand.아디다스)
        val 아디다스_상의 = makeProduct(Product.아디다스_상의, Brand.아디다스)

        val products = Products(listOf(나이키_상의, 나이키_양말, 아디다스_양말, 아디다스_신발, 아디다스_상의, 나이키_신발))

        val result = products.getMinPriceGroupByCategory()
        val expect = Products(listOf(나이키_상의, 아디다스_양말, 나이키_신발))

        asserThatProducts(expect, result)
    }

    @Test
    fun `최대가격상품 을 리턴한다`() {
        val 나이키_상의 = makeProduct(Product.나이키_상의, Brand.나이키)
        val 아디다스_상의 = makeProduct(Product.아디다스_상의, Brand.아디다스)

        val products = Products(listOf(나이키_상의, 아디다스_상의))

        val result = Products(listOf(products.getMaxPriceProduct()))
        val expect = Products(listOf(아디다스_상의))

        asserThatProducts(expect, result)
    }

    @Test
    fun `상품의 총 가격의 합을 구한다`() {
        val 나이키_상의 = makeProduct(Product.나이키_상의, Brand.나이키)
        val 아디다스_상의 = makeProduct(Product.아디다스_상의, Brand.아디다스)

        val products = Products(listOf(나이키_상의, 아디다스_상의))

        val result = products.sumTotalMinPrice()

        assertThat(result).isEqualByComparingTo(BigDecimal(93000))
    }


    private fun asserThatProducts(expect: Products, result: Products) {
        expect.productEntity.forEachIndexed { index, expectProduct ->
            val resultProduct = result.productEntity.get(index)
            assertThat(expectProduct.id).isEqualTo(resultProduct.id)
            assertThat(expectProduct.price.getValue()).isEqualTo(resultProduct.price.getValue())
            assertThat(expectProduct.category.id).isEqualTo(resultProduct.category.id)
            assertThat(expectProduct.brand.id).isEqualTo(resultProduct.brand.id)
            assertThat(expectProduct.isDeleted).isEqualTo(resultProduct.isDeleted)
        }
    }
}