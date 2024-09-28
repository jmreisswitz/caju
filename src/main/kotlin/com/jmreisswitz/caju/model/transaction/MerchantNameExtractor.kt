package com.jmreisswitz.caju.model.transaction

fun interface MerchantNameExtractor {

    fun extractFrom(merchant: String): String

}
