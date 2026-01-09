package com.rabahi.vibe.base44.grocery

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.string.shouldContain
import java.time.LocalDate

class EngineTest : BehaviorSpec({
    val products = listOf(
        Product("soup", "tin", 0.65),
        Product("bread", "loaf", 0.80),
        Product("milk", "bottle", 1.30),
        Product("apples", "single", 0.10)
    )

    val discounts = listOf(
        Discount(
            "Soup & Bread Deal", "Buy 2 soups, get bread half price",
            DiscountType.BUNDLE, "soup", 2, "bread", 50.0, -1, 6
        ),
        Discount(
            "Apple Discount", "10% off apples",
            DiscountType.PERCENTAGE, targetProduct = "apples",
            discountPercentage = 10.0, validFromDays = 3, validToEndOfNextMonth = true
        )
    )
    val sut = Engine(products, discounts)
    given("a discount of products") {
        `when`(" my basket has 3 soups + 2 breads today") {
            val result = sut.calculateBasket(mapOf("soup" to 3, "bread" to 2))

            then("my bill should be 3.15") {
                result.toString() shouldContain "total=3.15"
            }
        }
        `when`("my basket has 6 apples + milk in 5 days") {
            val result = sut.calculateBasket(
                mapOf("apples" to 6, "milk" to 1),
                LocalDate.now().plusDays(5)
            )
            then("my bill should be 1.84") {
                result.toString() shouldContain "total=1.84"
            }
        }
        `when`("my basket has 6 apples + milk today") {
            val result = sut.calculateBasket(
                mapOf("apples" to 6, "milk" to 1)
            )
            then("my bill should be 1.90") {
                result.toString() shouldContain "total=1.90"
            }
        }
        `when`("my basket has  3 apples, 2 tins of soup and a loaf of bread in5 days") {
            val result = sut.calculateBasket(
                mapOf("apples" to 3, "soup" to 2, "bread" to 1),
                LocalDate.now().plusDays(5)
            )
            then("my bill should be 1.97") {
                result.toString() shouldContain "total=1.97"
            }
        }
    }
})
