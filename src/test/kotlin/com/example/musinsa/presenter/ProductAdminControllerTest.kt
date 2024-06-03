package com.example.musinsa.presenter


import com.example.musinsa.domain.entity.ProductEntity
import com.example.musinsa.domain.enums.CategoryCode
import com.example.musinsa.infrastructure.repository.BrandJpaRepository
import com.example.musinsa.infrastructure.repository.CategoryJpaRepository
import com.example.musinsa.infrastructure.repository.ProductJpaRepository
import com.example.musinsa.presenter.request.AddProductRequest
import com.example.musinsa.presenter.request.UpdateProductRequest
import com.example.musinsa.support.IntegrationTest
import io.restassured.RestAssured
import io.restassured.http.ContentType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.data.repository.findByIdOrNull
import java.math.BigDecimal


@IntegrationTest
class ProductAdminControllerTest(
    private val productJpaRepository: ProductJpaRepository,
    private val brandJpaRepository: BrandJpaRepository,
    private val categoryJpaRepository: CategoryJpaRepository,
    private val productAdminController: ProductAdminController
) {
    @Test
    fun `상품을 추가한다`() {
        val request = AddProductRequest(
            brandId = 2,
            categoryCode = CategoryCode.TOP,
            price = BigDecimal(9999)
        )

        val result = productAdminController.addProduct(request)
            .body

        assertThat(result?.brandName)?.isEqualTo("B")
        assertThat(result?.categoryCode?.description)?.isEqualTo(CategoryCode.TOP.description)
        assertThat(result?.price)?.isEqualByComparingTo(BigDecimal(9999))

        cleanDataBase(result?.productId)
    }

    @Test
    fun `상품을 업데이트한다`() {
        val updatePrice = BigDecimal(9999)
        val updateBrandId = 3L
        val request = UpdateProductRequest(
            updateCategoryCode = CategoryCode.TOP,
            updateBrandId = updateBrandId,
            updatePrice = updatePrice
        )

        val addData = addData()

        val result = productAdminController.updateProduct(request, addData.id!!)
            .body

        assertThat(result).isNotNull
        assertThat(result?.price).isEqualByComparingTo(updatePrice)

        cleanDataBase(addData.id!!)
    }

    @Test
    fun `상품을 삭제한다`() {
        val addData = addData()

        productAdminController.deleteProduct(addData.id!!)

        val expect = productJpaRepository.findByIdOrNull(addData.id!!)

        assertThat(expect?.isDeleted).isTrue

        cleanDataBase(addData.id!!)
    }

    private fun addData(): ProductEntity {
        val brand = brandJpaRepository.findByIdOrNull(1L)
        val category = categoryJpaRepository.findByCode(CategoryCode.TOP)
        val product = ProductEntity.of(brand!!, category!!, BigDecimal(5000))

        return productJpaRepository.save(product)
    }

    private fun cleanDataBase(id: Long?) {
        productJpaRepository.deleteById(id!!)
    }
}