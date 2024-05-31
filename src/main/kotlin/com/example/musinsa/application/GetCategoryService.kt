package com.example.musinsa.application

import com.example.musinsa.common.ErrorCode
import com.example.musinsa.common.ImplementService
import com.example.musinsa.common.NotFoundException
import com.example.musinsa.domain.entity.CategoryEntity
import com.example.musinsa.domain.enums.CategoryCode
import com.example.musinsa.infrastructure.CategoryProvider

@ImplementService
class GetCategoryService(
    private val categoryProvider: CategoryProvider
) {

    fun findByCode(categoryCode: CategoryCode): CategoryEntity {
        return categoryProvider.findNullableByCategoryCode(categoryCode)
            ?: throw NotFoundException(ErrorCode.NOT_FOUND_CATEGORY)
    }
}