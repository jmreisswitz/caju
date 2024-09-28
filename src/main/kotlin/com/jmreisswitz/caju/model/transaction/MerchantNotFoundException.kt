package com.jmreisswitz.caju.model.transaction

class MerchantNotFoundException(merchant: String) :
    RuntimeException("Merchant $merchant not found") {
}
