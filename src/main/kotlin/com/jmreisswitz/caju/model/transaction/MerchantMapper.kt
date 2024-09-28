package com.jmreisswitz.caju.model.transaction

import com.jmreisswitz.caju.model.account.BalanceType

class MerchantMapper(
    private val map: Map<String, BalanceType>
) {

    fun mapToBalanceType(merchant: String): BalanceType {
        return map[merchant] ?: throw MerchantNotFoundException(merchant)
    }

}
