package com.example.musinsa.application

import com.example.musinsa.domain.enums.CategoryCode
import com.example.musinsa.fixture.*
import com.example.musinsa.infrastructure.ProductProvider
import com.example.musinsa.support.UnitTest
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.math.BigDecimal

@UnitTest
class SetProductServiceTest {

    private val productProvider: ProductProvider = mockk()
    private val setProductService: SetProductService =
        SetProductService(
            productProvider = productProvider
        )

    @Test
    fun `상품 엔티티로 변환 후 상품을 추가한다`() {
        val requestBrand = makeBrand(Brand.캉골)
        val requestCategoryCode = makeCategory(CategoryCode.TOP)
        val product = makeProduct(Product.캉골_상의, Brand.캉골)

        coEvery { productProvider.save(any()) } returns product

        val result =
            setProductService.addProduct(requestBrand, requestCategoryCode, product.price.getValue())

        assertThat(result.id).isEqualTo(product.id)
        assertThat(result.price.getValue()).isEqualTo(product.price.getValue())
        assertThat(result.isDeleted).isFalse

        verify(exactly = 1) {
            productProvider.save(any())
        }
    }

    @Test
    fun `상품 엔티티의 isDeleted 를 true 로 업데이트한다`() {
        val product = makeProduct(Product.캉골_패딩, Brand.캉골)

        val result = setProductService.delete(product)

        assertThat(result.id).isEqualTo(product.id)
        assertThat(result.isDeleted).isTrue
    }

    @Test
    fun `상품의 브랜드 카테고리, 가격을 업데이트한다`() {
        val findProduct = makeProduct(Product.아디다스_상의, Brand.아디다스)
        val updateBrand = makeBrand(Brand.노스페이스)
        val updateCategory = makeCategory(CategoryCode.SNEAKERS)
        val updatePrice = BigDecimal(100)

        val result =
            setProductService.update(findProduct, updateBrand, updateCategory, updatePrice)

        assertThat(result.id).isEqualTo(findProduct.id)
        assertThat(result.brand.id).isEqualTo(updateBrand.id)
        assertThat(result.category.code).isEqualTo(updateCategory.code)
        assertThat(result.price.getValue()).isEqualTo(updatePrice)
    }
}