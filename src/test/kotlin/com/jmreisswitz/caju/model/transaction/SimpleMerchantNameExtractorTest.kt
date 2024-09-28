package com.jmreisswitz.caju.model.transaction

import org.junit.jupiter.api.Test

class SimpleMerchantNameExtractorTest {

    @Test
    fun `given a merchant name with one word should return the first words`() {
        val merchantName = "Caju         PORTO ALEGRE BR"
        val simpleMerchantNameExtractor = SimpleMerchantNameExtractor()

        val result = simpleMerchantNameExtractor.extractFrom(merchantName)

        val expected = "Caju"
        assert(result == expected)
    }

    @Test
    fun `given a merchant name with two words should return the first two words`() {
        val merchantName = "Caju Restaurante    PORTO ALEGRE BR"
        val simpleMerchantNameExtractor = SimpleMerchantNameExtractor()

        val result = simpleMerchantNameExtractor.extractFrom(merchantName)

        val expected = "Caju Restaurante"
        assert(result == expected)
    }

    @Test
    fun `given a merchant name with three words should return the first three words`() {
        val merchantName = "Caju Restaurante Bar    PORTO ALEGRE BR"
        val simpleMerchantNameExtractor = SimpleMerchantNameExtractor()

        val result = simpleMerchantNameExtractor.extractFrom(merchantName)

        val expected = "Caju Restaurante Bar"
        assert(result == expected)
    }

    @Test
    fun `given an empty merchant name should return an empty string`() {
        val merchantName = ""
        val simpleMerchantNameExtractor = SimpleMerchantNameExtractor()

        val result = simpleMerchantNameExtractor.extractFrom(merchantName)

        val expected = ""
        assert(result == expected)
    }

    @Test
    fun `given a merchant name that does not follow a pattern then return entire string`() {
        val merchantName = "Caju restaurant"
        val simpleMerchantNameExtractor = SimpleMerchantNameExtractor()

        val result = simpleMerchantNameExtractor.extractFrom(merchantName)

        val expected = "Caju restaurant"
        assert(result == expected)
    }

}