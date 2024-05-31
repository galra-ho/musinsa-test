package com.example.musinsa.presenter

import com.example.musinsa.application.GetPriceRankApplicationService
import com.example.musinsa.presenter.response.MinPriceByCategoryResponse
import com.example.musinsa.common.CommonResponse
import com.example.musinsa.domain.enums.CategoryCode
import com.example.musinsa.presenter.response.MinPriceBrandResponse
import com.example.musinsa.presenter.response.SearchCategoryResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class RankController(
    private val getMinPriceRankService: GetPriceRankApplicationService
) {
    @GetMapping("/category/min-price")
    fun getMinPriceByCategory(): ResponseEntity<MinPriceByCategoryResponse> {
        val result = getMinPriceRankService.getBrandAndProductByCategory()
        return CommonResponse.convert(result)
    }

    @GetMapping("/brand/min-price")
    fun getMinPriceByBrand(): ResponseEntity<MinPriceBrandResponse> {
        val result = getMinPriceRankService.getBrand()
        return CommonResponse.convert(result)
    }

    @GetMapping("/price/search")
    fun searchMinAndMaxPriceBrandByCategory(
        @RequestParam category: CategoryCode
    ): ResponseEntity<SearchCategoryResponse> {
        val result = getMinPriceRankService.searchMinAndMaxPriceBrandByCategory(category)
        return CommonResponse.convert(result)
    }
}