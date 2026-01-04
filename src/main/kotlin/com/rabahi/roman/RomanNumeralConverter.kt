package com.rabahi.roman

class RomanNumeralConverter {

    val romanDoodahs = "IVXLCDM  "

    fun convertRomanNumeral(input: Int): String {
        val result = StringBuilder()
        listOf(
            Pair(1000, "M  "),
            Pair(100, "CDM"),
            Pair(10, "XLC"),
            Pair(1, "IVX"),
        ).forEach { result.append(deriveNumeral(input / it.first % 10, it.second)) }

        return result.toString()
    }

    private fun deriveNumeral(input: Int, numerals: String): String {
        val lower = 0
        val middle = 1
        val higher = 2
        val result = StringBuilder()
        when (input) {
            in 1..3 -> result
                .append(numerals.substring(lower, lower + 1).repeat(input))

            4 ->
                result
                    .append(numerals.substring(lower, lower + 1))
                    .append(numerals.substring(middle, middle + 1))

            5 ->
                result
                    .append(numerals.substring(middle, middle + 1))

            in 6..8 -> result
                .append(numerals.substring(middle, middle + 1))
                .append(numerals.substring(lower, lower + 1).repeat(input - 5))

            9 -> result
                .append(numerals.substring(lower, lower + 1))
                .append(numerals.substring(higher, higher + 1))
        }
        return result.toString()
    }
}