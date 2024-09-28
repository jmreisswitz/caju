package com.jmreisswitz.caju.model.transaction.converter

import com.jmreisswitz.caju.model.account.BalanceType
import com.jmreisswitz.caju.model.transaction.Transaction
import com.jmreisswitz.caju.model.transaction.TransactionToBalanceTypeConverter

class TransactionToBalanceTypeConverterWithFallback : TransactionToBalanceTypeConverter {
    override fun convert(transaction: Transaction): BalanceType {
        TODO("Not yet implemented")
    }
}