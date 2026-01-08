package com.rabahi.mark.grocery

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class MarkGroceryTest : BehaviorSpec({
    val sut = MarkGrocery()
    given("i want to price up my basket") {
        `when`("i have 3 tins of soup and 2 loaves of bread, bought today,") {
            val pricing = sut.pricing()
            then(" total cost = 3.15") {
                pricing shouldBe 3.15
            }
        }
    }
})