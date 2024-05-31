package com.example.musinsa.application

import com.example.musinsa.application.dto.MinPriceBrand
import com.example.musinsa.application.request.MinPriceByCategoryResponse
import com.example.musinsa.application.request.MinPriceProductResponse
import com.example.musinsa.common.ApplicationService
import com.example.musinsa.domain.entity.BrandEntity
import com.example.musinsa.domain.entity.CategoryEntity
import com.example.musinsa.domain.enums.CategoryCode
import com.example.musinsa.infrastructure.BrandProvider
import com.example.musinsa.infrastructure.CategoryProvider
import com.example.musinsa.infrastructure.ProductProvider
import com.example.musinsa.presenter.response.MinPriceBrandResponse
import com.example.musinsa.presenter.response.SearchCategoryResponse
import org.springframework.transaction.annotation.Transactional
import java.lang.IllegalArgumentException

@ApplicationService
class GetPriceRankService(
    private val categoryProvider: CategoryProvider,
    private val brandProvider: BrandProvider,
    private val productProvider: ProductProvider
) {
    @Transactional(readOnly = true)
    fun getBrandAndProductByCategory(): MinPriceByCategoryResponse {
        val categories = categoryProvider.findAll()
        val brandMap = brandProvider.findAll()
            .associateBy { it.id }
        val result = makeMinPriceProduct(categories, brandMap)

        return MinPriceByCategoryResponse.from(result)
    }

    private fun makeMinPriceProduct(
        categories: List<CategoryEntity>,
        brandMap: Map<Long, BrandEntity>
    ): List<MinPriceProductResponse> {
        return categories.map { category ->
            val product = productProvider.findByCategory(category).getMinPriceProduct()
            val brand = brandMap[product.brand.id] ?: throw IllegalArgumentException()
            MinPriceProductResponse.of(product, brand, category)
        }
    }

    @Transactional(readOnly = true)
    fun getBrand(): MinPriceBrandResponse {
        val brands = brandProvider.findAll()
        val categoriesMap = categoryProvider.findAll()
            .associateBy { it.id }
        // 카테고리별 최저가격 브랜드를 구한다
        val minPriceBrandByCategory = getMinPriceBrandByCategory(brands)

        return MinPriceBrandResponse.of(minPriceBrandByCategory, categoriesMap)
    }

    private fun getMinPriceBrandByCategory(brands: List<BrandEntity>): MinPriceBrand {
        return brands.map {
            val result =
                productProvider.findAllByCategoryAndBrand(brand = it)
                    .getMinPriceByCategory()

            MinPriceBrand.of(result, it)
        }.minBy { it.minPrice }
    }

    @Transactional(readOnly = true)
    fun searchMinAndMaxPriceBrandByCategory(code: CategoryCode): SearchCategoryResponse {
        val category = categoryProvider.findByCategoryCode(code)
        val products = productProvider.findByCategory(category)
        val minPriceProduct = products.getMinPriceProduct()
        val maxPriceProduct = products.getMaxPriceProduct(products.productEntity)

        val brandMap = brandProvider.findAllByIdIn(
            listOf(minPriceProduct.brand.id, maxPriceProduct.brand.id)
        ).associateBy { it.id }


        return SearchCategoryResponse.of(
            category = category,
            minPriceBrand = brandMap[minPriceProduct.brand.id],
            maxPriceBrand = brandMap[maxPriceProduct.brand.id],
            maxPriceProduct = maxPriceProduct,
            minPriceProduct = minPriceProduct
        )
    }
}