package com.jmreisswitz.caju.application

import com.jmreisswitz.caju.model.account.AccountNotFoundException
import com.jmreisswitz.caju.model.account.AccountRepository
import com.jmreisswitz.caju.model.authorizer.AuthorizationStatus
import com.jmreisswitz.caju.model.authorizer.TransactionAuthorizer
import com.jmreisswitz.caju.model.authorizer.TransactionAuthorizerResult
import com.jmreisswitz.caju.model.transaction.Transaction
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TransactionService(
    private val accountRepository: AccountRepository,
    private val transactionAuthorizer: TransactionAuthorizer
) {

    @Transactional
    fun authorize(transaction: Transaction): TransactionAuthorizerResult {
        return try {
            tryToAuthorize(transaction)
        } catch (e: AccountNotFoundException) {
            TransactionAuthorizerResult(AuthorizationStatus.ERROR)
        }
    }

    private fun tryToAuthorize(transaction: Transaction): TransactionAuthorizerResult {
        val account = accountRepository.findById(transaction.accountId)
            .orElseThrow { AccountNotFoundException(transaction.accountId) }
        val result = transactionAuthorizer.authorize(transaction, account)
        if (result.authorizationStatus == AuthorizationStatus.APPROVED) {
            accountRepository.save(account)
        }
        return result
    }

}