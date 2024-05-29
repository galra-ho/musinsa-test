package com.example.musinsa.domain.entity

import jakarta.persistence.*

@Entity
@Table(name = "brand")
data class BrandEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(name = "name")
    val name: String,

    @Column(name = "is_deleted")
    val isDeleted: Boolean,
): BaseEntity()