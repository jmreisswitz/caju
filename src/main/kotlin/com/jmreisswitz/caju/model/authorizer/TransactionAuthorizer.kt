package com.jmreisswitz.caju.model.authorizer

import com.jmreisswitz.caju.model.account.Account
import com.jmreisswitz.caju.model.transaction.Transaction

fun interface TransactionAuthorizer {
    fun authorize(transaction: Transaction, account: Account): TransactionAuthorizerResult
}