package com.example.musinsa.domain.entity

import com.example.musinsa.domain.enums.CategoryCode
import jakarta.persistence.*

@Entity
@Table(name = "product")
class ProductEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(name = "brand_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    val brand: BrandEntity,

    @Column(name = "category", nullable = false)
    val category: CategoryCode,

    @Column(name = "price", nullable = false)
    val price: Int
) : BaseEntity()