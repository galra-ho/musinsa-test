package com.example.musinsa.domain.entity

import jakarta.persistence.*

@Entity
@Table(name = "brand")
class BrandEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "name")
    var name: String,

    @Column(name = "is_deleted")
    var isDeleted: Boolean,
): BaseEntity() {
    fun update(brandName: String): BrandEntity {
        this.name = brandName
        return this
    }

    fun delete(): BrandEntity {
        this.isDeleted = true
        return this
    }

    companion object {
        fun from(name: String): BrandEntity {
            return BrandEntity(
                name = name,
                isDeleted = false
            )
        }
    }
}