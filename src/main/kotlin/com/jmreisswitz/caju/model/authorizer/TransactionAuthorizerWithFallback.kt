package com.jmreisswitz.caju.model.authorizer

import com.jmreisswitz.caju.model.account.Account
import com.jmreisswitz.caju.model.transaction.Transaction

class TransactionAuthorizerWithFallback(
    private val default: TransactionAuthorizer,
    private val fallback: TransactionAuthorizer
) : TransactionAuthorizer {

    override fun authorize(transaction: Transaction, account: Account): TransactionAuthorizerResult {
        val result = default.authorize(transaction, account)
        if (result.authorizationStatus == AuthorizationStatus.APPROVED) {
            return result
        }
        return fallback.authorize(transaction, account)
    }

}