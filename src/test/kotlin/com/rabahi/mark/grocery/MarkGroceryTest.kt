package com.rabahi.mark.grocery

import com.rabahi.mark.grocery.domain.Basket
import com.rabahi.mark.grocery.domain.StockItem
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class MarkGroceryTest : BehaviorSpec({
    val sut = MarkGrocery()
    given("i want to price up my basket") {
        `when`("i have 3 tins of soup and 2 loaves of bread, bought today,") {
            val basket = Basket()
            basket.addItem(StockItem.SOUP)
            basket.addItem(StockItem.SOUP)
            basket.addItem(StockItem.SOUP)
            basket.addItem(StockItem.BREAD)
            basket.addItem(StockItem.BREAD)
            val subTotal = sut.pricing(basket)
            val pricing = sut.applyDiscount(basket, subTotal)
            then(" total cost = 3.15") {
                pricing shouldBe 3.15
            }
        }
    }
})