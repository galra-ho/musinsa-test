package com.example.musinsa.application

import com.example.musinsa.common.ErrorCode
import com.example.musinsa.common.ImplementService
import com.example.musinsa.common.NotFoundException
import com.example.musinsa.domain.entity.CategoryEntity
import com.example.musinsa.domain.enums.CacheType
import com.example.musinsa.domain.enums.CategoryCode
import com.example.musinsa.domain.enums.LocalCacheName.CATEGORY_CACHE_NAME
import com.example.musinsa.infrastructure.CategoryProvider
import org.springframework.cache.annotation.Cacheable

@ImplementService
class GetCategoryService(
    private val categoryProvider: CategoryProvider
) {
    fun getByCode(categoryCode: CategoryCode): CategoryEntity {
        return categoryProvider.findNullableByCategoryCode(categoryCode)
            ?: throw NotFoundException(ErrorCode.NOT_FOUND_CATEGORY)
    }

    @Cacheable(CATEGORY_CACHE_NAME)
    fun getAll(): List<CategoryEntity> {
        return categoryProvider.findAll()
    }

    fun getCategoryMap(): Map<Long, CategoryEntity> {
        return getAll()
            .associateBy { it.id!! }
    }

    fun getNullableByCategoryCode(code: CategoryCode): CategoryEntity {
        return categoryProvider.findNullableByCategoryCode(code)
            ?: throw NotFoundException(ErrorCode.NOT_FOUND_CATEGORY)
    }
}