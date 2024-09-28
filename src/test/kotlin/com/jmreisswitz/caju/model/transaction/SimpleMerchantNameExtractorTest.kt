package com.jmreisswitz.caju.model.transaction

import org.junit.jupiter.api.Test

class SimpleMerchantNameExtractorTest {

    @Test
    fun `given a merchant name with one word should return the first words`() {
        val merchantName = Merchant("Caju         PORTO ALEGRE BR")
        val simpleMerchantNameExtractor = SimpleMerchantNameExtractor()

        val result = simpleMerchantNameExtractor.extractFrom(merchantName)

        val expected = MerchantName( "Caju")
        assert(result == expected)
    }

    @Test
    fun `given a merchant name with two words should return the first two words`() {
        val merchantName = Merchant("Caju Restaurante    PORTO ALEGRE BR")
        val simpleMerchantNameExtractor = SimpleMerchantNameExtractor()

        val result = simpleMerchantNameExtractor.extractFrom(merchantName)

        val expected = MerchantName( "Caju Restaurante")
        assert(result == expected)
    }

    @Test
    fun `given a merchant name with three words should return the first three words`() {
        val merchantName = Merchant("Caju Restaurante Bar    PORTO ALEGRE BR")
        val simpleMerchantNameExtractor = SimpleMerchantNameExtractor()

        val result = simpleMerchantNameExtractor.extractFrom(merchantName)

        val expected = MerchantName( "Caju Restaurante Bar")
        assert(result == expected)
    }

    @Test
    fun `given an empty merchant name should return an empty string`() {
        val merchantName = Merchant("")
        val simpleMerchantNameExtractor = SimpleMerchantNameExtractor()

        val result = simpleMerchantNameExtractor.extractFrom(merchantName)

        val expected = MerchantName( "")
        assert(result == expected)
    }

    @Test
    fun `given a merchant name that does not follow a pattern then return entire string`() {
        val merchantName = Merchant("Caju restaurant")
        val simpleMerchantNameExtractor = SimpleMerchantNameExtractor()

        val result = simpleMerchantNameExtractor.extractFrom(merchantName)

        val expected = MerchantName( "Caju restaurant")
        assert(result == expected)
    }

}