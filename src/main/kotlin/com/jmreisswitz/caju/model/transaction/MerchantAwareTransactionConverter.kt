package com.jmreisswitz.caju.model.transaction

import com.jmreisswitz.caju.model.account.AccountId

class MerchantAwareTransactionConverter(
    private val fallBackConverter: TransactionRequestConverter,
    private val merchantMapper: MerchantMapper
): TransactionRequestConverter {


    override fun convert(transactionRequest: TransactionRequest): Transaction {
        return try {
            tryToConvert(transactionRequest)
        } catch (e: MerchantNotFoundException) {
            fallBackConverter.convert(transactionRequest)
        }
    }

    private fun tryToConvert(transactionRequest: TransactionRequest) = Transaction(
        AccountId(transactionRequest.accountId),
        transactionRequest.totalAmount,
        merchantMapper.mapToBalanceType(transactionRequest.merchant),
        transactionRequest.merchant
    )
}