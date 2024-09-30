package com.jmreisswitz.caju.model.authorizer

import com.jmreisswitz.caju.model.account.Account
import com.jmreisswitz.caju.model.transaction.Transaction
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class TransactionAuthorizerWithFallback(
    private val default: TransactionAuthorizer,
    private val fallback: TransactionAuthorizer
) : TransactionAuthorizer {

    private val logger: Logger = LoggerFactory.getLogger(TransactionAuthorizerWithFallback::class.java)

    override fun authorize(transaction: Transaction, account: Account): TransactionAuthorizerResult {
        try {
            return tryToAuthorize(transaction, account)
        } catch (e: Exception) {
            logger.error("Error authorizing transaction", e)
            return TransactionAuthorizerResult(AuthorizationStatus.ERROR)
        }
    }

    private fun tryToAuthorize(
        transaction: Transaction,
        account: Account
    ): TransactionAuthorizerResult {
        val result = default.authorize(transaction, account)
        if (result.authorizationStatus == AuthorizationStatus.APPROVED) {
            return result
        }
        return fallback.authorize(transaction, account)
    }

}