package com.jmreisswitz.caju.model.authorizer

import com.jmreisswitz.caju.model.account.Account
import com.jmreisswitz.caju.model.account.BalanceType
import com.jmreisswitz.caju.model.account.NotEnoughBalanceException
import com.jmreisswitz.caju.model.transaction.AccountTransaction
import com.jmreisswitz.caju.model.transaction.Transaction

class CashBalanceFallbackTransactionAuthorizer : TransactionAuthorizer {

    override fun authorize(transaction: Transaction, account: Account): TransactionAuthorizerResult {
        try {
            val accountTransaction = AccountTransaction(transaction.totalAmount, BalanceType.CASH)
            account.execute(accountTransaction)
            return TransactionAuthorizerResult(AuthorizationStatus.APPROVED)
        } catch (e: NotEnoughBalanceException) {
            return TransactionAuthorizerResult(AuthorizationStatus.DENIED)
        }
    }
}