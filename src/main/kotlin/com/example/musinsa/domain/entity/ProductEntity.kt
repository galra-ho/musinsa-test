package com.example.musinsa.domain.entity

import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "product")
class ProductEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    val brand: BrandEntity,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    val category: CategoryEntity,

    @Column(name = "price")
    val price: BigDecimal,

    @Column(name = "is_deleted")
    val isDeleted: Boolean,
) : BaseEntity()