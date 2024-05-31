package com.example.musinsa.application

import com.example.musinsa.application.dto.MinPriceAndMaxPriceProduct
import com.example.musinsa.common.ErrorCode
import com.example.musinsa.common.ImplementService
import com.example.musinsa.common.NotFoundException
import com.example.musinsa.domain.Products
import com.example.musinsa.domain.entity.BrandEntity
import com.example.musinsa.domain.entity.CategoryEntity
import com.example.musinsa.domain.entity.ProductEntity
import com.example.musinsa.infrastructure.ProductProvider

@ImplementService
class GetProductService(
    private val productProvider: ProductProvider
) {
    fun getById(id: Long): ProductEntity {
        return productProvider.findNullableById(id)
            ?: throw NotFoundException(ErrorCode.NOT_FOUND_PRODUCT)
    }

    fun getMinPriceCategory(category: CategoryEntity): ProductEntity {
        return productProvider.findByCategory(category)
            .getMinPriceProduct()
    }

    fun getMinPriceByBrand(brand: BrandEntity): Products {
        return productProvider.findAllBrand(brand)
            .getMinPriceByCategory()
    }

    fun getMinProductAndMaxProduct(category: CategoryEntity): MinPriceAndMaxPriceProduct {
        val products = productProvider.findByCategory(category)
        return MinPriceAndMaxPriceProduct.from(products)
    }
}