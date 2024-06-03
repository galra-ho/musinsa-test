package com.example.musinsa.presenter.integration

import com.example.musinsa.domain.entity.BrandEntity
import com.example.musinsa.infrastructure.BrandProvider
import com.example.musinsa.presenter.BrandAdminController
import com.example.musinsa.presenter.request.AddBrandRequest
import com.example.musinsa.presenter.request.UpdateBrandRequest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc

@WebMvcTest(BrandAdminController::class)
class BrandAdminControllerIntegrationTest {

/*    @Autowired
    lateinit var mockmvc: MockMvc

    @Autowired
    lateinit var brandAdminController: BrandAdminController

    @Test
    fun `브랜드를 추가한다`() {

        mockmvc
            .perform(post())

        val brandName = "nike"
        val request = AddBrandRequest(name = brandName)
        val result = brandAdminController.addBrand(request)

        assertThat(result.body?.name).isEqualTo(brandName)
    }

    @Test
    fun `브랜드를 업데이트를한다`() {
        val targetBrandName = "TARGET_BRAND_NAME"
        val targetBrand = brandProvider.save(BrandEntity.from(targetBrandName))

        val brandName = "adidas"
        val request = UpdateBrandRequest(name = brandName)
        val result =
            brandAdminController.updateBrand(request, targetBrand.id!!)

        assertThat(result.body?.name).isEqualTo(brandName)
    }

    @Test
    fun `브랜드를 업데이트를한다`() {
        val targetBrandName = "TARGET_BRAND_NAME"
        val targetBrand = brandProvider.save(BrandEntity.from(targetBrandName))

        val brandName = "adidas"
        val request = UpdateBrandRequest(name = brandName)
        val result =
            brandAdminController.updateBrand(request, targetBrand.id!!)

        assertThat(result.body?.name).isEqualTo(brandName)
    }*/
}