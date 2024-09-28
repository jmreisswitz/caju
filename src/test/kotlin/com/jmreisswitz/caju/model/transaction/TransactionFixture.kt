package com.jmreisswitz.caju.model.transaction

import com.jmreisswitz.caju.model.account.AccountId

class TransactionFixture(
    private val accountId: AccountId = AccountId("123"),
    private val totalAmount: Int = 100,
    private val mcc: Mcc = Mcc("1234"),
    private val merchant: Merchant = Merchant("Caju")

) {
    fun build(): Transaction {
        return Transaction(
            accountId = accountId,
            totalAmount = totalAmount,
            mcc = mcc,
            merchant = merchant
        )
    }

}