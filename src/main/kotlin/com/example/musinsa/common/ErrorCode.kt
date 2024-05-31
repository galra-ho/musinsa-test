package com.example.musinsa.common

enum class ErrorCode(
    val message: String
) {
    NOT_FOUND_PRICE("가격 정보가 존재하지 않습니다"),
    NOT_FOUND_BRAND("브랜드 정보가 존재하지 않습니다"),
    ALREADY_BRAND("이미 존재하는 브랜드 입니다"),
    NOT_FOUND_CATEGORY("카테고리 정보가 존재하지 않습니다"),
    PRICE_IS_NOT_ZERO("가격은 0원보다 커야합니다"),
    NOT_FOUND_PRODUCT("상품 정보가 존재하지 않습니다"),
}