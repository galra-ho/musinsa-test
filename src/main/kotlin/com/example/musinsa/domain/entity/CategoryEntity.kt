package com.example.musinsa.domain.entity

import com.example.musinsa.domain.enums.CategoryCode
import jakarta.persistence.*

@Entity
@Table(name = "category")
data class CategoryEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(name = "code")
    @Enumerated(EnumType.STRING)
    val code: CategoryCode
) : BaseEntity() {
}

