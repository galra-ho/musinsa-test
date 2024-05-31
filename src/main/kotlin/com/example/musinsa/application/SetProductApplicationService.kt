package com.example.musinsa.application

import com.example.musinsa.common.ApplicationService
import com.example.musinsa.presenter.request.AddProductRequest
import com.example.musinsa.presenter.request.UpdateProductRequest
import com.example.musinsa.presenter.response.*
import org.springframework.transaction.annotation.Transactional

@ApplicationService
class SetProductApplicationService(
    private val setProductService: SetProductService,
    private val getProductService: GetProductService,
    private val getBrandService: GetBrandService,
    private val getCategoryService: GetCategoryService
) {
    @Transactional
    fun add(request: AddProductRequest): AddProductResponse {
        val brand = getBrandService.getById(request.brandId)
        val category = getCategoryService.findByCode(request.categoryCode)
        val addProduct = setProductService.addProduct(brand, category, request.price)

        return AddProductResponse.from(addProduct)
    }

    @Transactional
    fun update(request: UpdateProductRequest, id: Long): UpdateProductResponse {
        val findProduct = getProductService.getById(id)
        val brand = getBrandService.getById(request.updateBrandId)
        val category = getCategoryService.findByCode(request.updateCategoryCode)
        val updateProduct = setProductService.update(
            findProduct, brand, category, request.updatePrice
        )

        return UpdateProductResponse.from(updateProduct)
    }

    @Transactional
    fun delete(id: Long): DeleteProductResponse {
        val findProduct = getProductService.getById(id)
        val deleteProduct = setProductService.delete(findProduct)
        return DeleteProductResponse.from(deleteProduct)
    }
}