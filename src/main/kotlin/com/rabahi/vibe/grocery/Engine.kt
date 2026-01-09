package com.rabahi.vibe.grocery

import java.time.LocalDate
import kotlin.math.roundToInt

class Engine(
    private val products: List<Product>,
    private val discounts: List<Discount>
) {
    fun calculateBasket(
        basketItems: Map<String, Int>,
        purchaseDate: LocalDate = LocalDate.now()
    ): PricingResult {
        val productMap = products.associateBy { it.name.lowercase() }

        // Calculate subtotal
        val subtotal = basketItems.entries.sumOf { (name, qty) ->
            productMap[name.lowercase()]?.price?.times(qty) ?: 0.0
        }

        // Apply discounts
        val appliedDiscounts = discounts
            .filter { isDiscountValid(it, purchaseDate) }
            .mapNotNull { discount ->
                val amount = calculateDiscountAmount(discount, basketItems, productMap)
                if (amount > 0) AppliedDiscount(discount.name, amount) else null
            }

        val totalDiscount = appliedDiscounts.sumOf { it.amount }

        return PricingResult(
            subtotal = subtotal,
            appliedDiscounts = appliedDiscounts,
            total = (subtotal - totalDiscount).roundTo2Decimals()
        )
    }

    private fun isDiscountValid(discount: Discount, purchaseDate: LocalDate): Boolean {
        val today = LocalDate.now()
        val validFrom = today.plusDays(discount.validFromDays.toLong())

        val validTo = when {
            discount.validToEndOfNextMonth -> today.plusMonths(1).withDayOfMonth(1).plusMonths(1).minusDays(1)
            discount.validToDays != null -> today.plusDays(discount.validToDays.toLong())
            else -> today.plusYears(100)
        }

        return purchaseDate in validFrom..validTo
    }

    private fun calculateDiscountAmount(
        discount: Discount,
        basket: Map<String, Int>,
        productMap: Map<String, Product>
    ): Double = when (discount.type) {
        DiscountType.BUNDLE -> calculateBundleDiscount(discount, basket, productMap)
        DiscountType.PERCENTAGE -> calculatePercentageDiscount(discount, basket, productMap)
    }

    private fun calculateBundleDiscount(
        discount: Discount,
        basket: Map<String, Int>,
        productMap: Map<String, Product>
    ): Double {
        val triggerQty = basket[discount.triggerProduct?.lowercase()] ?: 0
        val targetQty = basket[discount.targetProduct?.lowercase()] ?: 0
        val targetPrice = productMap[discount.targetProduct?.lowercase()]?.price ?: return 0.0

        if (triggerQty < discount.triggerQuantity || targetQty == 0) return 0.0

        val bundleApplications = triggerQty / discount.triggerQuantity
        val discountableItems = minOf(bundleApplications, targetQty)

        return (discountableItems * targetPrice * discount.discountPercentage / 100).roundTo2Decimals()
    }

    private fun calculatePercentageDiscount(
        discount: Discount,
        basket: Map<String, Int>,
        productMap: Map<String, Product>
    ): Double {
        val targetQty = basket[discount.targetProduct?.lowercase()] ?: 0
        val targetPrice = productMap[discount.targetProduct?.lowercase()]?.price ?: return 0.0

        if (targetQty == 0) return 0.0

        return (targetQty * targetPrice * discount.discountPercentage / 100).roundTo2Decimals()
    }

    private fun Double.roundTo2Decimals() = (this * 100).roundToInt() / 100.0
}