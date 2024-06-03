package com.example.musinsa.application

import com.example.musinsa.common.ErrorCode
import com.example.musinsa.domain.Products
import com.example.musinsa.domain.enums.CategoryCode
import com.example.musinsa.fixture.*
import com.example.musinsa.infrastructure.ProductProvider
import com.example.musinsa.support.UnitTest
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

@UnitTest
class GetProductServiceTest {

    private val productProvider: ProductProvider = mockk()
    private val getProductService = GetProductService(
        productProvider = productProvider
    )

    @Test
    fun `상품이 존재하지 않으면 예외가 발생한다`() {
        coEvery { productProvider.findNullableById(any()) } returns null

        assertThatThrownBy {
            getProductService.getById(12)
        }.hasMessage(ErrorCode.NOT_FOUND_PRODUCT.message)


        verify(exactly = 1) {
            productProvider.findNullableById(any())
        }
    }

    @Test
    fun `카테고리로 조회한 상품들 중 최소 가격 상품을 구한다`() {
        val 아디다스 = makeProduct(Product.아디다스_상의, Brand.아디다스)
        val 노스페이스 = makeProduct(Product.노스페이스_상의, Brand.노스페이스)
        val 나이키 = makeProduct(Product.나이키_상의, Brand.나이키)

        coEvery { productProvider.findByCategory(any()) } returns
                Products(listOf(아디다스, 노스페이스, 나이키))

        val result = getProductService.getMinPriceByCategory(makeCategory(CategoryCode.TOP))


        assertThat(result.id).isEqualTo(Product.나이키_상의.id)
        assertThat(result.price.getValue()).isEqualTo(Product.나이키_상의.price)

        verify(exactly = 1) {
            productProvider.findByCategory(any())
        }
    }

    @Test
    fun `브랜드로 조회한 상품들 중 최소 가격 상품을 구한다`() {
        val 아디다스 = makeProduct(Product.아디다스_신발, Brand.아디다스)
        val 나이키 = makeProduct(Product.나이키_신발, Brand.나이키)

        coEvery { productProvider.findAllBrand(any()) } returns
                Products(listOf(아디다스, 나이키))

        val result = getProductService.getMinPriceByBrand(makeBrand(Brand.나이키))


        asserThatProducts(Products(listOf(나이키)), result)

        verify(exactly = 1) {
            productProvider.findAllBrand(any())
        }
    }

    @Test
    fun `카테고로 조회한 상품들 중 최소 가격 상품과 최대 가격 상품을 구한다`() {
        val 아디다스 = makeProduct(Product.아디다스_신발, Brand.아디다스)
        val 나이키 = makeProduct(Product.나이키_신발, Brand.나이키)

        coEvery { productProvider.findByCategory(any()) } returns
                Products(listOf(아디다스, 나이키))

        val result = getProductService.getMinProductAndMaxProduct(makeCategory(CategoryCode.SNEAKERS))

        assertThat(result.maxPriceProduct.id).isEqualTo(아디다스.id)
        assertThat(result.maxPriceProduct.price).isEqualTo(아디다스.price)
        assertThat(result.minPriceProduct.id).isEqualTo(나이키.id)
        assertThat(result.minPriceProduct.price).isEqualTo(나이키.price)

        verify(exactly = 1) {
            productProvider.findByCategory(any())
        }
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