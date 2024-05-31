package com.example.musinsa.domain.enums

enum class CacheType(
    val cacheName: String,
    val expiredAt: Long,
    val maximumSize: Long
) {
    CATEGORY_CACHE(LocalCacheName.CATEGORY_CACHE_NAME, 60 * 60, 100);
}

object LocalCacheName {
    const val CATEGORY_CACHE_NAME = "CATEGORY_CACHE"
}