package com.example.musinsa.application

import com.example.musinsa.domain.BrandProducts
import com.example.musinsa.common.ApplicationService
import com.example.musinsa.common.ErrorCode
import com.example.musinsa.common.NotFoundException
import com.example.musinsa.domain.entity.BrandEntity
import com.example.musinsa.domain.entity.CategoryEntity
import com.example.musinsa.domain.enums.CategoryCode
import com.example.musinsa.domain.findMinPriceProductOfTheBrand
import com.example.musinsa.presenter.response.MinPriceBrandResponse
import com.example.musinsa.presenter.response.MinPriceByCategoryResponse
import com.example.musinsa.presenter.response.MinPriceProductResponse
import com.example.musinsa.presenter.response.SearchCategoryResponse
import org.springframework.transaction.annotation.Transactional

@ApplicationService
class GetPriceRankApplicationService(
    private val getCategoryService: GetCategoryService,
    private val getBrandService: GetBrandService,
    private val getProductService: GetProductService
) {
    @Transactional(readOnly = true)
    fun getBrandAndProductByCategory(): MinPriceByCategoryResponse {
        val categories = getCategoryService.getAll()
        val brandMap = getBrandService.getBrandMap()
        val result = makeMinPriceProduct(categories, brandMap)

        return MinPriceByCategoryResponse.from(result)
    }

    private fun makeMinPriceProduct(
        categories: List<CategoryEntity>,
        brandMap: Map<Long, BrandEntity>
    ): List<MinPriceProductResponse> {
        return categories.map { category ->
            val product = getProductService.getMinPriceByCategory(category)
            val brand = brandMap[product.brand.id]
                ?: throw NotFoundException(ErrorCode.NOT_FOUND_BRAND)
            MinPriceProductResponse.of(product, brand, category)
        }
    }

    @Transactional(readOnly = true)
    fun getMinPriceBrand(): MinPriceBrandResponse {
        val brands = getBrandService.getAll()
        val categoriesMap = getCategoryService.getCategoryMap()
        // 카테고리별 최저가격 브랜드를 구한다
        val minPriceBrandByCategory = getMinPriceBrandsByCategory(brands)

        return MinPriceBrandResponse.of(minPriceBrandByCategory, categoriesMap)
    }

    private fun getMinPriceBrandsByCategory(brands: List<BrandEntity>): BrandProducts {
        val brandProducts = brands.map {
            val result = getProductService.getMinPriceByBrand(it)
            BrandProducts.of(result, it)
        }

        return brandProducts.findMinPriceProductOfTheBrand()
    }

    @Transactional(readOnly = true)
    fun searchMinAndMaxPriceBrandByCategory(code: CategoryCode): SearchCategoryResponse {
        val category = getCategoryService.getNullableByCategoryCode(code)
        val minPriceAndMaxPriceProduct =
            getProductService.getMinProductAndMaxProduct(category)
        val brandIds = minPriceAndMaxPriceProduct.getBrandIds()
        val brandMap = getBrandService.getMapByBrandIds(brandIds)

        return SearchCategoryResponse.of(
            category = category,
            minPriceBrand = brandMap[minPriceAndMaxPriceProduct.minPriceProduct.brand.id],
            maxPriceBrand = brandMap[minPriceAndMaxPriceProduct.maxPriceProduct.brand.id],
            maxPriceProduct = minPriceAndMaxPriceProduct.maxPriceProduct,
            minPriceProduct = minPriceAndMaxPriceProduct.minPriceProduct
        )
    }
}