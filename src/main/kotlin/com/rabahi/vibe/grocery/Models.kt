package com.rabahi.vibe.grocery

data class Product(
    val name: String,
    val unit: String,
    val price: Double
)

data class Discount(
    val name: String,
    val description: String,
    val type: DiscountType,
    val triggerProduct: String? = null,
    val triggerQuantity: Int = 0,
    val targetProduct: String? = null,
    val discountPercentage: Double = 0.0,
    val validFromDays: Int = 0,
    val validToDays: Int? = null,
    val validToEndOfNextMonth: Boolean = false
)

enum class DiscountType { BUNDLE, PERCENTAGE }

data class PricingResult(
    val subtotal: Double,
    val appliedDiscounts: List<AppliedDiscount>,
    val total: Double
)

data class AppliedDiscount(val name: String, val amount: Double)
