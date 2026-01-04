package com.rabahi.roman

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import java.security.InvalidParameterException

class RomanNumeralConverterTest : BehaviorSpec({
    val sut = RomanNumeralConverter()

    given("A RomanNumeral Converter") {
        `when`("I enter 1") {
            val result = sut.convertRomanNumeral(1)
            then("I should get an 'I'") {
                result shouldBe "I"
            }
        }

        `when`("I enter 2") {
            val result = sut.convertRomanNumeral(2)
            then("I should get an 'II'") {
                result shouldBe "II"
            }
        }

        `when`("I enter 4") {
            val result = sut.convertRomanNumeral(4)
            then("I should get an 'IV'") {
                result shouldBe "IV"
            }
        }

        `when`("I enter 5") {
            val result = sut.convertRomanNumeral(5)
            then("I should get an 'V'") {
                result shouldBe "V"
            }
        }

        `when`("I enter 7") {
            val result = sut.convertRomanNumeral(7)
            then("I should get an 'VII'") {
                result shouldBe "VII"
            }
        }

        `when`("I enter 9") {
            val result = sut.convertRomanNumeral(9)
            then("I should get an 'IX'") {
                result shouldBe "IX"
            }
        }

        `when`("I enter 11") {
            val result = sut.convertRomanNumeral(11)
            then("I should get an 'XI'") {
                result shouldBe "XI"
            }
        }

        `when`("I enter 171") {
            val result = sut.convertRomanNumeral(171)
            then("I should get an 'XI'") {
                result shouldBe "CLXXI"
            }
        }

        `when`("I enter 1917") {
            val result = sut.convertRomanNumeral(1917)
            then("I should get an 'XI'") {
                result shouldBe "MCMXVII"
            }
        }

        `when`("I enter -1") {
            val result = shouldThrow<InvalidParameterException> { sut.convertRomanNumeral(-1) }
            then("I should get an exception'") {
                result.message shouldBe "input must be between 1 and 4999"
            }
        }

        `when`("I enter 5928") {
            val result = shouldThrow<InvalidParameterException> { sut.convertRomanNumeral(5928) }
            then("I should get an exception'") {
                result.message shouldBe "input must be between 1 and 4999"
            }
        }
    }

})
