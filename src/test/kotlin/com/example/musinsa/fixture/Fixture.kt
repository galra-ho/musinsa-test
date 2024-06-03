package com.example.musinsa.fixture

import com.example.musinsa.domain.ProductPrice
import com.example.musinsa.domain.entity.BrandEntity
import com.example.musinsa.domain.entity.CategoryEntity
import com.example.musinsa.domain.entity.ProductEntity
import com.example.musinsa.domain.enums.CategoryCode
import java.math.BigDecimal


enum class Brand(
    val id: Long,
    val brandName: String,
) {
    나이키(1, "나이키"),
    아디다스(2, "아디다스"),
    노스페이스(3, "노스페이스"),
    캉골(4, "캉골")
}

enum class Product(
    val id: Long,
    val category: CategoryCode,
    val price: BigDecimal
) {
    나이키_양말(1, CategoryCode.SOCKS, BigDecimal(3000)),
    아디다스_양말(2, CategoryCode.SOCKS, BigDecimal(2000)),

    나이키_신발(3, CategoryCode.SNEAKERS, BigDecimal(60000)),
    아디다스_신발(4, CategoryCode.SNEAKERS, BigDecimal(70000)),

    노스페이스_패딩(5, CategoryCode.OUTER, BigDecimal(300000)),
    캉골_패딩(6, CategoryCode.OUTER, BigDecimal(250000)),

    노스페이스_상의(7, CategoryCode.TOP, BigDecimal(70000)),
    캉골_상의(8, CategoryCode.TOP, BigDecimal(65000)),

    나이키_상의(9, CategoryCode.TOP, BigDecimal(40000)),
    아디다스_상의(10, CategoryCode.TOP, BigDecimal(53000)),
}

fun makeBrand(
    brand: Brand,
    isDeleted: Boolean = false
): BrandEntity {
    return BrandEntity(
        id = brand.id,
        name = brand.name,
        isDeleted = isDeleted
    )
}

fun makeProduct(
    product: Product,
    brand: Brand,
    isDeleted: Boolean = false
): ProductEntity {
    return ProductEntity(
        id = product.id,
        brand = makeBrand(brand),
        category = CategoryEntity(code = product.category, id = product.category.id),
        price = ProductPrice(product.price),
        isDeleted = isDeleted
    )
}

fun makeCategory(
    code: CategoryCode
): CategoryEntity {
    return CategoryEntity(
        id = code.id,
        code = code
    )
}