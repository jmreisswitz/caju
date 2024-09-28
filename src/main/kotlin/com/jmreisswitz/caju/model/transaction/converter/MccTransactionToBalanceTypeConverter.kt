package com.jmreisswitz.caju.model.transaction.converter

import com.jmreisswitz.caju.model.account.BalanceType
import com.jmreisswitz.caju.model.transaction.MccMapper
import com.jmreisswitz.caju.model.transaction.Transaction
import com.jmreisswitz.caju.model.transaction.TransactionToBalanceTypeConverter

class MccTransactionToBalanceTypeConverter(
    private val mccMapper: MccMapper
) : TransactionToBalanceTypeConverter {

    override fun convert(transaction: Transaction): BalanceType {
        return mccMapper.mapToBalanceType(transaction.mcc)
    }
}