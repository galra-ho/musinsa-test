package com.example.musinsa.application

import com.example.musinsa.application.dto.MinPriceBrand
import com.example.musinsa.application.request.MinPriceByCategoryResponse
import com.example.musinsa.application.request.MinPriceProductResponse
import com.example.musinsa.common.ApplicationService
import com.example.musinsa.domain.entity.BrandEntity
import com.example.musinsa.domain.entity.CategoryEntity
import com.example.musinsa.infrastructure.BrandProvider
import com.example.musinsa.infrastructure.CategoryProvider
import com.example.musinsa.infrastructure.ProductProvider
import com.example.musinsa.presenter.response.MinPriceBrandResponse
import org.springframework.transaction.annotation.Transactional
import java.lang.IllegalArgumentException

@ApplicationService
class GetMinPriceService(
    private val categoryProvider: CategoryProvider,
    private val brandProvider: BrandProvider,
    private val productProvider: ProductProvider,
    private val calculateMinPriceService: CalculateMinPriceService
) {
    @Transactional(readOnly = true)
    fun getBrandAndProductByCategory(): MinPriceByCategoryResponse {
        val categories = categoryProvider.getAll()
        val brandMap = brandProvider.getAll()
            .associateBy { it.id }
        val result = makeMinPriceProduct(categories, brandMap)

        return MinPriceByCategoryResponse.from(result)
    }

    private fun makeMinPriceProduct(
        categories: List<CategoryEntity>,
        brandMap: Map<Long, BrandEntity>
    ): List<MinPriceProductResponse> {
        return categories.map { category ->
            val product = productProvider.findByCategory(category).minByOrNull { it.price }
                ?: throw IllegalArgumentException()
            val brand = brandMap[product.brand.id] ?: throw IllegalArgumentException()
            MinPriceProductResponse.of(product, brand, category)
        }
    }

    @Transactional(readOnly = true)
    fun getBrand(): MinPriceBrandResponse {
        val brands = brandProvider.getAll()

        val categoriesMap = categoryProvider.getAll()
            .associateBy { it.id }

        val minPriceBrand = brands.map {
            val result = productProvider.findAllByCategoryAndBrand(brand = it)
                .getMinPriceByCategory()

            MinPriceBrand.of(result, it)
        }.minBy { it.minPrice }

        return MinPriceBrandResponse.from(minPriceBrand, categoriesMap)
    }

    @Transactional(readOnly = true)

    fun searchCategory() {
        TODO("Not yet implemented")
    }
}