package com.example.musinsa.presenter

import com.example.musinsa.application.SetProductApplicationService
import com.example.musinsa.common.CommonResponse
import com.example.musinsa.presenter.request.AddBrandRequest
import com.example.musinsa.presenter.request.AddProductRequest
import com.example.musinsa.presenter.request.UpdateBrandRequest
import com.example.musinsa.presenter.request.UpdateProductRequest
import com.example.musinsa.presenter.response.*
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class ProductAdminController(
    private val setProductApplicationService: SetProductApplicationService
) {

    @PostMapping("/admin/product")
    fun addProduct(
        @RequestBody request: AddProductRequest
    ): ResponseEntity<AddProductResponse> {
        val result = setProductApplicationService.add(request)
        return CommonResponse.convert(result)
    }

    @PatchMapping("/admin/product/{id}")
    fun updateProduct(
        @RequestBody request: UpdateProductRequest,
        @PathVariable id: Long
    ): ResponseEntity<UpdateProductResponse> {
        val result = setProductApplicationService.update(request, id)
        return CommonResponse.convert(result)
    }

    @DeleteMapping("/admin/product/{id}")
    fun deleteProduct(
        @PathVariable id: Long
    ): ResponseEntity<DeleteProductResponse> {
        val result = setProductApplicationService.delete(id)
        return CommonResponse.convert(result)
    }
}