package com.jmreisswitz.caju.application

import com.jmreisswitz.caju.model.authorizer.AuthorizationStatus
import com.jmreisswitz.caju.model.authorizer.TransactionAuthorizerResult
import com.jmreisswitz.caju.model.transaction.Transaction
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.concurrent.TimeUnit

@Service
class TransactionServiceWithLock(
    private val transactionService: TransactionService,
    private val distributedLock: DistributedLock
) {

    @Transactional
    fun authorize(transaction: Transaction): TransactionAuthorizerResult {
        return distributedLock.lock(transaction.accountId.value, 1L, TimeUnit.SECONDS) {
            transactionService.authorize(transaction)
        } ?: TransactionAuthorizerResult(AuthorizationStatus.ERROR)
    }

}