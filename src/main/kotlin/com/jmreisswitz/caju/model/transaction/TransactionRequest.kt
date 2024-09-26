package com.jmreisswitz.caju.model.transaction

class TransactionRequest(
    val accountId: String,
    val totalAmount: Int,
    val mcc: String,
    val merchant: String
) {
}