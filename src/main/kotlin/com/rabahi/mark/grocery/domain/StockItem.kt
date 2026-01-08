package com.rabahi.mark.grocery.domain

enum class StockItem(val unit:String, val cost:Double) {
    APPLES("single", 0.10),
    MILK("bottle", 1.30),
    BREAD("loaf", 0.80),
    SOUP("tim", 0.65),
}