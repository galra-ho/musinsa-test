package com.example.musinsa.application

import com.example.musinsa.application.dto.MinAndMaxPriceProductDto
import com.example.musinsa.common.ErrorCode
import com.example.musinsa.common.ImplementService
import com.example.musinsa.common.NotFoundException
import com.example.musinsa.domain.Products
import com.example.musinsa.domain.entity.BrandEntity
import com.example.musinsa.domain.entity.CategoryEntity
import com.example.musinsa.domain.entity.ProductEntity
import com.example.musinsa.infrastructure.ProductProvider
import org.springframework.transaction.annotation.Transactional

@ImplementService
class GetProductService(
    private val productProvider: ProductProvider
) {
    @Transactional(readOnly = true)
    fun getById(id: Long): ProductEntity {
        return productProvider.findNullableById(id)
            ?: throw NotFoundException(ErrorCode.NOT_FOUND_PRODUCT)
    }

    @Transactional(readOnly = true)
    fun getMinPriceByCategory(category: CategoryEntity): ProductEntity {
        return productProvider.findByCategory(category)
            .getMinPriceProduct()
    }

    @Transactional(readOnly = true)
    fun getMinPriceByBrand(brand: BrandEntity): Products {
        return productProvider.findAllBrand(brand)
            .getMinPriceGroupByCategory()
    }

    @Transactional(readOnly = true)
    fun getMinProductAndMaxProduct(category: CategoryEntity): MinAndMaxPriceProductDto {
        val products = productProvider.findByCategory(category)
        return MinAndMaxPriceProductDto.from(products)
    }
}