package com.example.musinsa.presenter

import com.example.musinsa.application.GetPriceRankService
import com.example.musinsa.domain.enums.CategoryCode
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class RankController(
    private val getMinPriceRankService: GetPriceRankService
) {

    @GetMapping("/category/min-price")
    fun getMinPriceByCategory() {
        getMinPriceRankService.getBrandAndProductByCategory()
    }

    @GetMapping("/brand/min-price")
    fun getMinPriceByBrand() {
        getMinPriceRankService.getBrand()
    }

    @GetMapping("/price/search")
    fun searchMinAndMaxPriceBrandByCategory(
        @RequestParam category: CategoryCode
    ) {
        getMinPriceRankService.searchMinAndMaxPriceBrandByCategory(category)
    }
}