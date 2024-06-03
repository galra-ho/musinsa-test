package com.example.musinsa.presenter

import com.example.musinsa.domain.entity.BrandEntity
import com.example.musinsa.support.fixture.Brand
import com.example.musinsa.infrastructure.repository.BrandJpaRepository
import com.example.musinsa.presenter.request.AddBrandRequest
import com.example.musinsa.presenter.request.UpdateBrandRequest
import com.example.musinsa.support.IntegrationTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.data.repository.findByIdOrNull


@IntegrationTest
class BrandAdminControllerIntegrationTest(
    private val brandAdminController: BrandAdminController,
    private val brandJpaRepository: BrandJpaRepository
) {

    @Test
    fun `브랜드를 추가한다`() {
        val name = Brand.커버낫.brandName
        val request = AddBrandRequest(
            name = name
        )

        val result =
            brandAdminController.addBrand(request)

        assertThat(result.body?.name).isEqualTo(name)
        cleanDataBase(result.body?.brandId)
    }

    private fun cleanDataBase(brandId: Long?) {
        brandJpaRepository.deleteById(brandId!!)
    }

    @Test
    fun `브랜드를 업데이트를한다`() {
        val targetBrandName = "TARGET_BRAND_NAME"
        val 업뎃대상 = "업뎃대상"
        val addData = addData(BrandEntity.from(targetBrandName))

        val request = UpdateBrandRequest(
            name = 업뎃대상
        )

        val result =
            brandAdminController.updateBrand(request, addData.id!!)
                .body
        assertThat(result?.name).isEqualTo(업뎃대상)

        cleanDataBase(result?.brandId)
    }

    private fun addData(brand: BrandEntity): BrandEntity {
        return brandJpaRepository.save(brand)
    }

    @Test
    fun `브랜드를 삭제한다`() {
        val targetBrandName = "DELETE_BRAND"
        val deleteData = addData(BrandEntity.from(targetBrandName))

        val result = brandAdminController.deleteBrand(deleteData.id!!)
            .body

        val expect = brandJpaRepository.findByIdOrNull(result?.brandId)

        assertThat(expect?.isDeleted).isTrue

        cleanDataBase(deleteData.id)
    }
}