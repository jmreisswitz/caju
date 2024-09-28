package com.jmreisswitz.caju.model.authorizer

import com.jmreisswitz.caju.model.account.Account
import com.jmreisswitz.caju.model.account.NotEnoughBalanceException
import com.jmreisswitz.caju.model.transaction.AccountTransaction
import com.jmreisswitz.caju.model.transaction.Transaction
import com.jmreisswitz.caju.model.transaction.TransactionToBalanceTypeConverter

class SimpleTransactionAuthorizer(
    private val transactionToBalanceTypeConverter: TransactionToBalanceTypeConverter
): TransactionAuthorizer {

    override fun authorize(transaction: Transaction, account: Account): TransactionAuthorizerResult {
        val balanceType = transactionToBalanceTypeConverter.convert(transaction)
        val accountTransaction = AccountTransaction(transaction.totalAmount, balanceType)
        try {
            account.execute(accountTransaction)
            return TransactionAuthorizerResult(AuthorizationStatus.APPROVED)
        } catch (e: NotEnoughBalanceException) {
            return TransactionAuthorizerResult(AuthorizationStatus.DENIED)
        }
    }
}