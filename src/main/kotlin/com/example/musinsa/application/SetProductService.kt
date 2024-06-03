package com.example.musinsa.application

import com.example.musinsa.common.ImplementService
import com.example.musinsa.domain.ProductPrice
import com.example.musinsa.domain.entity.BrandEntity
import com.example.musinsa.domain.entity.CategoryEntity
import com.example.musinsa.domain.entity.ProductEntity
import com.example.musinsa.infrastructure.ProductProvider
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal

@ImplementService
class SetProductService(
    private val productProvider: ProductProvider
) {
    @Transactional
    fun addProduct(brand: BrandEntity, category: CategoryEntity, price: BigDecimal): ProductEntity {
        val product = ProductEntity.of(brand, category, price)

        return productProvider.save(product)
    }

    @Transactional
    fun delete(product: ProductEntity): ProductEntity {
        return product.delete()
    }

    @Transactional
    fun update(findProduct: ProductEntity, brand: BrandEntity, category: CategoryEntity, updatePrice: BigDecimal): ProductEntity {
        return findProduct.update(brand, category, ProductPrice(updatePrice))
    }
}