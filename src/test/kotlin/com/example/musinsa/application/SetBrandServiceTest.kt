package com.example.musinsa.application

import com.example.musinsa.common.ErrorCode
import com.example.musinsa.support.fixture.Brand
import com.example.musinsa.support.fixture.makeBrand
import com.example.musinsa.infrastructure.BrandProvider
import com.example.musinsa.support.UnitTest
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

@UnitTest
class SetBrandServiceTest {

    private val brandProvider: BrandProvider = mockk()
    private val setBrandService: SetBrandService = SetBrandService(
        brandProvider = brandProvider
    )

    @Test
    fun `이미 등록된 브랜드가 존재한다면 예외가 발생한다`() {
        assertThatThrownBy {
            setBrandService.addBrand(makeBrand(Brand.캉골), Brand.캉골.brandName)
        }.hasMessage(ErrorCode.ALREADY_BRAND.message)

        verify(exactly = 0) {
            brandProvider.save(any())
        }
    }

    @Test
    fun `브랜드가 존재하지 않으면 상품을 등록한다`() {
        val brand = makeBrand(Brand.캉골)
        every { brandProvider.save(any()) } returns brand

        val result = setBrandService.addBrand(null, Brand.캉골.brandName)

        assertThat(result.id).isEqualTo(brand.id)
        assertThat(result.name).isEqualTo(brand.name)

        verify(exactly = 1) {
            brandProvider.save(any())
        }
    }

    @Test
    fun `브랜드를 업데이트한다`() {
        val brand = makeBrand(Brand.나이키)

        val result = setBrandService.update(brand, Brand.캉골.brandName)

        assertThat(result.id).isEqualTo(brand.id)
        assertThat(result.name).isEqualTo(brand.name)
    }

    @Test
    fun `브랜드 엔티티의 isDeleted 를 true 로 업데이트한다`() {
        val brand = makeBrand(Brand.나이키)

        val result = setBrandService.delete(brand)

        assertThat(result.id).isEqualTo(brand.id)
        assertThat(result.isDeleted).isTrue
    }
}