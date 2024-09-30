package com.jmreisswitz.caju.model.transaction

import com.jmreisswitz.caju.model.account.BalanceType

class MerchantMapper(
    private val merchantNameExtractor: MerchantNameExtractor,
    val merchantsMap: Map<MerchantName, BalanceType>
) {

    fun mapToBalanceType(merchant: Merchant): BalanceType {
        val merchantName = merchantNameExtractor.extractFrom(merchant)
        return merchantsMap[merchantName] ?: throw MerchantNotFoundException(merchant.value)
    }
}
