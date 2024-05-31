package com.example.musinsa.config

import com.example.musinsa.domain.enums.CacheType
import com.github.benmanes.caffeine.cache.Caffeine.newBuilder
import org.springframework.cache.CacheManager
import org.springframework.cache.caffeine.CaffeineCache
import org.springframework.cache.support.SimpleCacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.concurrent.TimeUnit

@Configuration
class LocalCacheConfig {
    @Bean
    fun caffeineLocalCache(): CacheManager {
        val cacheManager = SimpleCacheManager()
        val caches = CacheType.entries.map {
            CaffeineCache(
                it.cacheName,
                newBuilder()
                    .expireAfterWrite(it.expiredAt, TimeUnit.SECONDS)
                    .maximumSize(it.maximumSize)
                    .build()
            )
        }

        cacheManager.setCaches(caches)
        return cacheManager
    }
}