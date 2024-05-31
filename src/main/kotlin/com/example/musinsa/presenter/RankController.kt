package com.example.musinsa.presenter

import com.example.musinsa.application.GetMinPriceService
import com.example.musinsa.domain.enums.CategoryCode
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class RankController(
    private val getMinPriceService: GetMinPriceService
) {

    @GetMapping("/category/min-price")
    fun getMinPriceByCategory() {
        getMinPriceService.getBrandAndProductByCategory()
    }

    @GetMapping("/brand/min-price")
    fun getMinPriceByBrand() {
        getMinPriceService.getBrand()
    }

    @GetMapping("/price/search")
    fun searchMinAndMaxPriceBrandByCategory(
        @RequestParam category: CategoryCode
    ) {
        getMinPriceService.searchByCategory(category)
    }
}