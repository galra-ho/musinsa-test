package com.example.musinsa.application

import com.example.musinsa.fixture.Brand
import com.example.musinsa.fixture.makeBrand
import com.example.musinsa.support.UnitTest
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

@UnitTest
class SetBrandApplicationServiceTest {

    private val setBrandService: SetBrandService = mockk()
    private val getBrandService: GetBrandService = mockk()

    private val setBrandApplicationService = SetBrandApplicationService(
        setBrandService = setBrandService,
        getBrandService = getBrandService
    )
    @Test
    fun `브랜드를 등록할때 기존에 등록된 브랜드가 존재하는지 체크한 후 등록한다`() {
        val addBrand = makeBrand(Brand.나이키)

        coEvery { getBrandService.getNullableByName(any()) } returns null
        coEvery { setBrandService.addBrand(any(), any()) } returns addBrand


        val result = setBrandApplicationService.add(Brand.나이키.brandName)

        assertThat(result.name).isEqualTo(addBrand.name)

        verify(exactly = 1) {
            getBrandService.getNullableByName(any())
            setBrandService.addBrand(any(), any())
        }
    }

    @Test
    fun `브랜드의 이름을 변경할때 기존에 등록된 브랜드가 존재하는지 체크한 후 변경한다`() {
        val findBrand = makeBrand(Brand.나이키)
        val updateBrand = makeBrand(Brand.나이키)

        coEvery { getBrandService.getById(any()) } returns findBrand
        coEvery { setBrandService.update(any(), any()) } returns updateBrand


        val result = setBrandApplicationService.update(Brand.나이키.brandName, Brand.나이키.id)

        assertThat(result.name).isEqualTo(updateBrand.name)

        verify(exactly = 1) {
            getBrandService.getById(any())
            setBrandService.update(any(), any())
        }
    }

    @Test
    fun `브랜드의 이름을 삭제할때 기존에 등록된 브랜드가 존재하는지 체크한 후 삭제한다`() {
        val findBrand = makeBrand(Brand.나이키)
        val deleteBoard = makeBrand(Brand.나이키)

        coEvery { getBrandService.getById(any()) } returns findBrand
        coEvery { setBrandService.delete(any()) } returns deleteBoard

        val result = setBrandApplicationService.delete(Brand.나이키.id)

        assertThat(result.name).isEqualTo(deleteBoard.name)

        verify(exactly = 1) {
            getBrandService.getById(any())
            setBrandService.delete(any())
        }
    }
}