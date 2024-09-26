package com.jmreisswitz.caju.model.transaction

import com.jmreisswitz.caju.model.account.AccountId

class MccAwareTransactionRequestConverter(
    private val mccTransactionMapper: MccTransactionMapper
) : TransactionRequestConverter {

    override fun convert(transactionRequest: TransactionRequest): Transaction {
        return Transaction(
            AccountId(transactionRequest.accountId),
            transactionRequest.totalAmount,
            mccTransactionMapper.map(transactionRequest.mcc),
            transactionRequest.merchant,
        )
    }
}