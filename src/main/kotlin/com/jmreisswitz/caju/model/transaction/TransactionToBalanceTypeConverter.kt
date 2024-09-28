package com.jmreisswitz.caju.model.transaction

import com.jmreisswitz.caju.model.account.BalanceType

fun interface TransactionToBalanceTypeConverter {
    fun convert(transaction: Transaction): BalanceType
}