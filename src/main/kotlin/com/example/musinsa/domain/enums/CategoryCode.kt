package com.example.musinsa.domain.enums

import org.springframework.context.annotation.Description

enum class CategoryCode(
    val description: String
) {
    TOP("상의"),
    OUTER("아우터"),
    PANTS("바지"),
    SNEAKERS("스니커즈"),
    BAG("가방"),
    HAT("모자"),
    SOCKS("양말"),
    ACCESSORY("액세서리");
}