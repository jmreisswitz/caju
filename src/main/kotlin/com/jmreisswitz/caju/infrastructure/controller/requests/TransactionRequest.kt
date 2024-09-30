package com.jmreisswitz.caju.infrastructure.controller.requests

import com.jmreisswitz.caju.model.account.AccountId
import com.jmreisswitz.caju.model.transaction.Mcc
import com.jmreisswitz.caju.model.transaction.Merchant
import com.jmreisswitz.caju.model.transaction.Transaction

data class TransactionRequest(
    val accountId: String,
    val totalAmount: Double,
    val mcc: String,
    val merchant: String
) {

    fun asTransactionModel(): Transaction {
        return Transaction(
            AccountId(accountId),
            (totalAmount*100).toInt(),
            Mcc(mcc) ,
            Merchant(merchant)
        )
    }

}
