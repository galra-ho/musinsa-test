package com.example.musinsa.domain.entity

import jakarta.persistence.*

@Entity
@Table(name = "brand")
class BrandEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(name = "name", nullable = false)
    val name: String,

    @Column(name = "isDeleted", nullable = false)
    val isDeleted: Boolean,
): BaseEntity()