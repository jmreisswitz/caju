package com.jmreisswitz.caju.model.transaction

class SimpleMerchantNameExtractor : MerchantNameExtractor {

    override fun extractFrom(merchant: String): String {
        return merchant.split("  ").first()
    }
}