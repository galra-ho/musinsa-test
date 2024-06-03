package com.example.musinsa.domain.enums

enum class CategoryCode(
    val description: String,
    val id: Long
) {
    TOP("상의", 1),
    OUTER("아우터", 2),
    PANTS("바지", 3),
    SNEAKERS("스니커즈", 4),
    BAG("가방", 5),
    HAT("모자", 6),
    SOCKS("양말", 7),
    ACCESSORY("액세서리", 8);
}