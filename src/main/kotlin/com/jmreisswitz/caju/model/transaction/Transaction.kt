package com.jmreisswitz.caju.model.transaction

import com.jmreisswitz.caju.model.account.AccountId

class Transaction(
    val accountId: AccountId,
    val totalAmount: Int,
    val mcc: Mcc,
    val merchant: Merchant
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Transaction

        if (accountId != other.accountId) return false
        if (totalAmount != other.totalAmount) return false
        if (mcc != other.mcc) return false
        if (merchant != other.merchant) return false

        return true
    }

    override fun hashCode(): Int {
        var result = accountId.hashCode()
        result = 31 * result + totalAmount
        result = 31 * result + mcc.hashCode()
        result = 31 * result + merchant.hashCode()
        return result
    }
}