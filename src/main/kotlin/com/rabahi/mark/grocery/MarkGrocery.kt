package com.rabahi.mark.grocery

import com.rabahi.mark.grocery.domain.Basket

class MarkGrocery {
    fun pricing(basket: Basket):Double = basket.total()
    fun applyDiscount(basket: Basket, subTotal: Double) :Double {
        if (basket.hasThreeSoupAndTwoBread(basket)) {
            return subTotal-0.40
        }
        return subTotal
    }
}