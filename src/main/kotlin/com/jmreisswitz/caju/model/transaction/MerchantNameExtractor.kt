package com.jmreisswitz.caju.model.transaction

fun interface MerchantNameExtractor {

    fun extractFrom(merchant: Merchant): MerchantName

}
