package com.example.musinsa.application

import com.example.musinsa.application.request.MinPriceByCategoryResponse
import com.example.musinsa.application.request.MinPriceProductResponse
import com.example.musinsa.common.ApplicationService
import com.example.musinsa.domain.entity.BrandEntity
import com.example.musinsa.domain.entity.CategoryEntity
import com.example.musinsa.infrastructure.BrandProvider
import com.example.musinsa.infrastructure.CategoryProvider
import com.example.musinsa.infrastructure.ProductProvider
import java.lang.IllegalArgumentException

@ApplicationService
class GetMinPriceService(
    private val categoryProvider: CategoryProvider,
    private val brandProvider: BrandProvider,
    private val productProvider: ProductProvider,
    private val calculateMinPriceService: CalculateMinPriceService
) {
    fun getBrandAndProductByCategory(): MinPriceByCategoryResponse {
        val categories = categoryProvider.getAll()
        val brandMap = brandProvider.getAll()
            .associateBy { it.id }
        val result = makeMinPriceProduct(categories, brandMap)

        return MinPriceByCategoryResponse.from(result)
    }

    private fun makeMinPriceProduct(categories: List<CategoryEntity>, brandMap: Map<Long, BrandEntity>): List<MinPriceProductResponse> {
        return categories.map { category ->
            val product = productProvider.findByCategory(category.code).minByOrNull { it.price }
                ?: throw IllegalArgumentException()
            val brand = brandMap[product.brand.id] ?: throw IllegalArgumentException()
            MinPriceProductResponse.of(product, brand, category)
        }

    }

    fun getBrand() {
        TODO("Not yet implemented")
    }

    fun searchCategory() {
        TODO("Not yet implemented")
    }
}