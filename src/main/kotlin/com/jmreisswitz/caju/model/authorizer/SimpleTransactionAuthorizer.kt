package com.jmreisswitz.caju.model.authorizer

import com.jmreisswitz.caju.model.account.Account
import com.jmreisswitz.caju.model.account.NotEnoughBalanceException
import com.jmreisswitz.caju.model.transaction.AccountTransaction
import com.jmreisswitz.caju.model.transaction.MccMapper
import com.jmreisswitz.caju.model.transaction.Transaction

class SimpleTransactionAuthorizer(
    private val mccMapper: MccMapper
): TransactionAuthorizer {
    override fun authorize(transaction: Transaction, account: Account): TransactionAuthorizerResult {
        val balanceType = mccMapper.mapToBalanceType(transaction.mcc)
        val accountTransaction = AccountTransaction(transaction.totalAmount, balanceType)
        try {
            account.execute(accountTransaction)
            return TransactionAuthorizerResult(AuthorizationStatus.APPROVED)
        } catch (e: NotEnoughBalanceException) {
            return TransactionAuthorizerResult(AuthorizationStatus.DENIED)
        }
    }
}