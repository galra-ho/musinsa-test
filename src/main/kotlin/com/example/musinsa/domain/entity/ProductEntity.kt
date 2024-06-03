package com.example.musinsa.domain.entity

import com.example.musinsa.domain.ProductPrice
import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "product")
data class ProductEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    var brand: BrandEntity,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    var category: CategoryEntity,

    @Embedded
    var price: ProductPrice,

    @Column(name = "is_deleted")
    var isDeleted: Boolean,
) : BaseEntity() {
    fun delete(): ProductEntity {
        this.isDeleted = true
        return this
    }

    fun update(
        brand: BrandEntity,
        category: CategoryEntity,
        price: ProductPrice
    ): ProductEntity {
        this.brand = brand
        this.category = category
        this.price = price
        return this
    }

    companion object {
        fun of(
            brand: BrandEntity,
            category: CategoryEntity,
            price: BigDecimal
        ): ProductEntity {
            return ProductEntity(
                brand = brand,
                category = category,
                price = ProductPrice(price),
                isDeleted = false
            )
        }
    }
}