package com.example.musinsa.application

import com.example.musinsa.common.ApplicationService
import com.example.musinsa.presenter.response.AddBrandResponse
import com.example.musinsa.presenter.response.DeleteBrandResponse
import com.example.musinsa.presenter.response.UpdateBrandResponse
import org.springframework.transaction.annotation.Transactional

@ApplicationService
class SetBrandApplicationService(
    private val setBrandService: SetBrandService,
    private val getBrandService: GetBrandService
) {
    @Transactional
    fun add(brandName: String): AddBrandResponse {
        val findBrand = getBrandService.getNullableByName(brandName)
        val addBrand = setBrandService.addBrand(findBrand, brandName)
        return AddBrandResponse.from(addBrand)
    }

    @Transactional
    fun update(brandName: String, id: Long): UpdateBrandResponse {
        val findBrand = getBrandService.getById(id)
        val updateBoard = setBrandService.update(findBrand, brandName)
        return UpdateBrandResponse.from(updateBoard)
    }

    @Transactional
    fun delete(id: Long): DeleteBrandResponse {
        val findBrand = getBrandService.getById(id)
        val updateBoard = setBrandService.delete(findBrand)
        return DeleteBrandResponse.from(updateBoard)
    }
}