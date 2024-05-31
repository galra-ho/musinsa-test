package com.example.musinsa.presenter.response

import com.example.musinsa.domain.entity.BrandEntity
import java.time.LocalDateTime

data class UpdateBrandResponse(
    val name: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
) {
    companion object {
        fun from(updateBoard: BrandEntity): UpdateBrandResponse =
            UpdateBrandResponse(
                name = updateBoard.name,
                createdAt = updateBoard.createdAt,
                updatedAt = updateBoard.updatedAt
            )
    }
}