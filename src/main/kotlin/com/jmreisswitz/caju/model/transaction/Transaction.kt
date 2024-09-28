package com.jmreisswitz.caju.model.transaction

import com.jmreisswitz.caju.model.account.AccountId

class Transaction(
    val accountId: AccountId,
    val totalAmount: Int,
    val mcc: Mcc,
    val merchant: Merchant
) {
}