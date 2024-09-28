package com.jmreisswitz.caju.model.transaction.converter

import com.jmreisswitz.caju.model.account.BalanceType
import com.jmreisswitz.caju.model.transaction.MerchantMapper
import com.jmreisswitz.caju.model.transaction.MerchantNotFoundException
import com.jmreisswitz.caju.model.transaction.Transaction
import com.jmreisswitz.caju.model.transaction.TransactionToBalanceTypeConverter

class MerchantTransactionToBalanceTypeConverter(
    private val merchantMapper: MerchantMapper
) : TransactionToBalanceTypeConverter {

    override fun convert(transaction: Transaction): BalanceType {
        try {
            return merchantMapper.mapToBalanceType(transaction.merchant)
        } catch (e: MerchantNotFoundException) {
            throw TransactionToBalanceTypeConverterNotPossible()
        }
    }
}