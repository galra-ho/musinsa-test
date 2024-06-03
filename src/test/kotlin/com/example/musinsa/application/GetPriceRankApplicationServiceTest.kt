package com.example.musinsa.application

import com.example.musinsa.application.dto.MinAndMaxPriceProductDto
import com.example.musinsa.domain.Products
import com.example.musinsa.domain.enums.CategoryCode
import com.example.musinsa.fixture.*
import com.example.musinsa.support.UnitTest
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

@UnitTest
class GetPriceRankApplicationServiceTest {

    private val getCategoryService: GetCategoryService = mockk()
    private val getBrandService: GetBrandService = mockk()
    private val getProductService: GetProductService = mockk()
    private val getPriceRankApplicationService = GetPriceRankApplicationService(
        getCategoryService = getCategoryService,
        getBrandService = getBrandService,
        getProductService = getProductService
    )

    @Test
    fun `카테고리별 최저가격 브랜드와 상품을 조회한다`() {
        val categories =
            listOf(makeCategory(CategoryCode.SOCKS))
        val brandMap = listOf(
            makeBrand(Brand.나이키), makeBrand(Brand.아디다스)
        ).associateBy { it.id!! }

        val 나이키_양말 = makeProduct(Product.나이키_양말, Brand.나이키)

        every { getCategoryService.getAll() } returns categories
        every { getBrandService.getBrandMap() } returns brandMap
        every { getProductService.getMinPriceByCategory(any()) } returns 나이키_양말

        val result = getPriceRankApplicationService.getBrandAndProductByCategory()
        assertThat(result.totalPrice).isEqualTo(나이키_양말.price.getValue())
        assertThat(result.minPriceProductInfo[0].price).isEqualTo(나이키_양말.price.getValue())
        assertThat(result.minPriceProductInfo[0].brandName).isEqualTo(나이키_양말.brand.name)
        assertThat(result.minPriceProductInfo[0].categoryCode).isEqualTo(나이키_양말.category.code)
    }

    @Test
    fun `단일 브랜드로 모든 카테고리 상품을 구매할때 촤저가격에 판매하는 브랜드를 구한다`() {
        val categoryCodeMap = listOf(
            makeCategory(CategoryCode.SOCKS),
            makeCategory(CategoryCode.SNEAKERS),
            makeCategory(CategoryCode.TOP)
        ).associateBy { it.id!! }

        val 나이키_신발 = makeProduct(Product.나이키_신발, Brand.나이키)
        val 나이키_상의 = makeProduct(Product.나이키_상의, Brand.나이키)
        val 나이키_양말 = makeProduct(Product.나이키_양말, Brand.나이키)
        val 아디다스_양말 = makeProduct(Product.아디다스_양말, Brand.아디다스)
        val 아디다스_신발 = makeProduct(Product.아디다스_신발, Brand.아디다스)
        val 아디다스_상의 = makeProduct(Product.아디다스_상의, Brand.아디다스)


        every { getBrandService.getAll() } returns listOf(makeBrand(Brand.나이키), makeBrand(Brand.아디다스))

        every { getCategoryService.getCategoryMap() } returns categoryCodeMap
        every { getProductService.getMinPriceByBrand(makeBrand(Brand.아디다스)) } returns
                Products(listOf(아디다스_양말, 아디다스_신발, 아디다스_상의))
        every { getProductService.getMinPriceByBrand(makeBrand(Brand.나이키)) } returns
                Products(listOf(나이키_신발, 나이키_상의, 나이키_양말))



        val result = getPriceRankApplicationService.getMinPriceBrand()
        assertThat(result.brandName).isEqualTo(Brand.나이키.name)
        assertThat(result.categories[0].price).isEqualTo(나이키_신발.price.getValue())
        assertThat(result.categories[0].code).isEqualTo(나이키_신발.category.code)

        assertThat(result.categories[1].price).isEqualTo(나이키_상의.price.getValue())
        assertThat(result.categories[1].code).isEqualTo(나이키_상의.category.code)

        assertThat(result.categories[1].price).isEqualTo(나이키_상의.price.getValue())
        assertThat(result.categories[1].code).isEqualTo(나이키_상의.category.code)
    }

    @Test
    fun `카테고리 이름으로 최저, 최고 가격 브랜드와 상품 가격을 조회한다`() {
        val brandMap = listOf(
            makeBrand(Brand.나이키),
            makeBrand(Brand.아디다스),
            makeBrand(Brand.노스페이스),
        ).associateBy { it.id!! }

        val 나이키_상의 = makeProduct(Product.나이키_상의, Brand.나이키)
        val 노스페이스_상의 = makeProduct(Product.노스페이스_상의, Brand.노스페이스)

        every { getCategoryService.getNullableByCategoryCode(any()) } returns makeCategory(CategoryCode.TOP)
        every { getProductService.getMinProductAndMaxProduct(any()) } returns
                MinAndMaxPriceProductDto(나이키_상의, 노스페이스_상의)
        every { getBrandService.getMapByBrandIds(any()) } returns brandMap

        val result = getPriceRankApplicationService.searchMinAndMaxPriceBrandByCategory(CategoryCode.TOP)

        assertThat(result.category).isEqualTo(CategoryCode.TOP)
        assertThat(result.minPriceBrand[0].brandName).isEqualTo(Brand.나이키.brandName)
        assertThat(result.minPriceBrand[0].price).isEqualTo(나이키_상의.price.getValue())
        assertThat(result.maxPriceBrand[0].brandName).isEqualTo(Brand.노스페이스.brandName)
        assertThat(result.maxPriceBrand[0].price).isEqualTo(노스페이스_상의.price.getValue())
    }
}