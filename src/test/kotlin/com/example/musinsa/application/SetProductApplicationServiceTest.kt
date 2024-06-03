package com.example.musinsa.application

import com.example.musinsa.domain.enums.CategoryCode
import com.example.musinsa.fixture.*
import com.example.musinsa.presenter.request.AddProductRequest
import com.example.musinsa.presenter.request.UpdateProductRequest
import com.example.musinsa.support.UnitTest
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

@UnitTest
class SetProductApplicationServiceTest {

    private val setProductService: SetProductService = mockk()
    private val getProductService: GetProductService = mockk()
    private val getBrandService: GetBrandService = mockk()
    private val getCategoryService: GetCategoryService = mockk()

    private val setProductApplicationService = SetProductApplicationService(
        setProductService = setProductService,
        getProductService = getProductService,
        getBrandService = getBrandService,
        getCategoryService = getCategoryService
    )

    @Test
    fun `상품을 등록할때 브랜드와 카테고리코드가 존재하는지 체크 한 후 등록한다`() {
        val addBrand = makeBrand(Brand.노스페이스)
        val findCategoryCode = makeCategory(code = CategoryCode.TOP)
        val product = makeProduct(Product.노스페이스_상의, Brand.노스페이스)

        val request = AddProductRequest(
            brandId = addBrand.id!!,
            categoryCode = findCategoryCode.code,
            price = product.price.getValue()
        )

        every { getBrandService.getById(any()) } returns addBrand
        every { getCategoryService.getByCode(any()) } returns findCategoryCode
        every { setProductService.addProduct(any(), any(), any()) } returns product

        val result = setProductApplicationService.add(request)

        assertThat(result.productId).isEqualTo(product.id)
        assertThat(result.price).isEqualByComparingTo(product.price.getValue())
        assertThat(result.categoryCode).isEqualTo(product.category.code)
        assertThat(result.brandName).isEqualTo(addBrand.name)

        verify(exactly = 1) {
            getBrandService.getById(any())
            getCategoryService.getByCode(CategoryCode.TOP)
            setProductService.addProduct(any(), any(), any())
        }
    }

    @Test
    fun `상품을 변경할때 상품, 브랜드 카테고리코드가 존재하는지 체크 한 후 업데이트한다`() {
        val findBrand = makeBrand(Brand.노스페이스)
        val findCategoryCode = makeCategory(code = CategoryCode.TOP)
        val findProduct = makeProduct(Product.노스페이스_상의, Brand.노스페이스)

        val request = UpdateProductRequest(
            updateBrandId = findBrand.id!!,
            updateCategoryCode = findCategoryCode.code,
            updatePrice = findProduct.price.getValue()
        )

        every { getProductService.getById(any()) } returns findProduct
        every { getBrandService.getById(any()) } returns findBrand
        every { getCategoryService.getByCode(any()) } returns makeCategory(code = CategoryCode.TOP)
        every { setProductService.update(any(), any(), any(), any()) } returns makeProduct(Product.노스페이스_상의, Brand.노스페이스)

        val result = setProductApplicationService.update(request, findProduct.id!!)

        assertThat(result.price).isEqualByComparingTo(findProduct.price.getValue())
        assertThat(result.categoryCode).isEqualTo(findProduct.category.code.description)
        assertThat(result.brandName).isEqualTo(findBrand.name)

        verify(exactly = 1) {
            getProductService.getById(any())
            getBrandService.getById(any())
            getCategoryService.getByCode(CategoryCode.TOP)
            setProductService.update(any(), any(), any(), any())
        }
    }

    @Test
    fun `상품을 삭제 할때 상품, 브랜드 카테고리코드가 존재하는지 체크 한 후 삭제한다`() {
        val findProduct = makeProduct(Product.노스페이스_상의, Brand.노스페이스)

        every { getProductService.getById(any()) } returns findProduct
        every { setProductService.delete(any()) } returns makeProduct(Product.노스페이스_상의, Brand.노스페이스)

        val result = setProductApplicationService.delete(findProduct.id!!)

        assertThat(result.productId).isEqualTo(findProduct.id)

        verify(exactly = 1) {
            getProductService.getById(any())
            setProductService.delete(any())
        }
    }
}