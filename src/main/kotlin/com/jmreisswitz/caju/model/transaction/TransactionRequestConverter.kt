package com.jmreisswitz.caju.model.transaction

fun interface TransactionRequestConverter {
    fun convert(transactionRequest: TransactionRequest): Transaction
}