package com.jmreisswitz.caju.infrastructure.controller.responses

import com.jmreisswitz.caju.model.account.BalanceType
import com.jmreisswitz.caju.model.transaction.MerchantName

data class MerchantMapResponse(
    val merchants: Map<String, String>
) {
    companion object {
        fun from(merchants: Map<MerchantName, BalanceType>): MerchantMapResponse {
            return MerchantMapResponse(
                merchants.map { it.key.value to it.value.name }.toMap()
            )
        }
    }
}
