package com.example.musinsa.presenter

import com.example.musinsa.application.SetBrandApplicationService
import com.example.musinsa.common.CommonResponse
import com.example.musinsa.presenter.request.AddBrandRequest
import com.example.musinsa.presenter.request.UpdateBrandRequest
import com.example.musinsa.presenter.response.AddBrandResponse
import com.example.musinsa.presenter.response.DeleteBrandResponse
import com.example.musinsa.presenter.response.UpdateBrandResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class BrandAdminController(
    private val setBrandApplicationService: SetBrandApplicationService
) {
    @PostMapping("/admin/brand")
    fun addBrand(
        @RequestBody request: AddBrandRequest
    ): ResponseEntity<AddBrandResponse> {
        val result = setBrandApplicationService.add(request.name)
        return CommonResponse.convert(result)
    }

    @PatchMapping("/admin/brand/{id}")
    fun updateBrand(
        @RequestBody request: UpdateBrandRequest,
        @PathVariable id: Long
    ): ResponseEntity<UpdateBrandResponse> {
        val result = setBrandApplicationService.update(request.name, id)
        return CommonResponse.convert(result)
    }

    @DeleteMapping("/admin/brand/{id}")
    fun deleteBrand(
        @PathVariable id: Long
    ): ResponseEntity<DeleteBrandResponse> {
        val result = setBrandApplicationService.delete(id)
        return CommonResponse.convert(result)
    }
}