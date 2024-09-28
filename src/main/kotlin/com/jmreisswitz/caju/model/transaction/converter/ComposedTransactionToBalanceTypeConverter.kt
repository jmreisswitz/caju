package com.jmreisswitz.caju.model.transaction.converter

import com.jmreisswitz.caju.model.account.BalanceType
import com.jmreisswitz.caju.model.transaction.Transaction
import com.jmreisswitz.caju.model.transaction.TransactionToBalanceTypeConverter

class ComposedTransactionToBalanceTypeConverter(
    private val converters: List<TransactionToBalanceTypeConverter>
) : TransactionToBalanceTypeConverter {

    override fun convert(transaction: Transaction): BalanceType {
        for (converter in converters) {
            try {
                return converter.convert(transaction)
            } catch (e: TransactionToBalanceTypeConverterNotPossible) {
                continue
            }
        }
        throw TransactionToBalanceTypeConverterNotPossible()
    }
}