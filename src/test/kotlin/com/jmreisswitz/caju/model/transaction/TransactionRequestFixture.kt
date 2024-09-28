package com.jmreisswitz.caju.model.transaction

class TransactionRequestFixture(
    private val accountId: String = "123",
    private val totalAmount: Int = 100,
    private val mcc: String = "123",
    private val merchant: String = "Caju"

) {
    fun build(): TransactionRequest {
        return TransactionRequest(
            accountId = accountId,
            totalAmount = totalAmount,
            mcc = mcc,
            merchant = merchant
        )
    }

}