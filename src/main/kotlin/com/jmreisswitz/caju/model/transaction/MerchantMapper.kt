package com.jmreisswitz.caju.model.transaction

import com.jmreisswitz.caju.model.account.BalanceType

class MerchantMapper(
    private val merchantNameExtractor: MerchantNameExtractor,
    private val map: Map<MerchantName, BalanceType>
) {

    fun mapToBalanceType(merchant: Merchant): BalanceType {
        val merchantName = merchantNameExtractor.extractFrom(merchant)
        return map[merchantName] ?: throw MerchantNotFoundException(merchant.value)
    }
}
