package com.jmreisswitz.caju.model.transaction

class SimpleMerchantNameExtractor : MerchantNameExtractor {

    override fun extractFrom(merchant: Merchant): MerchantName {
        val merchantName = merchant.value.split("  ").first()
        return MerchantName(merchantName)
    }
}