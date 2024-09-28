package com.jmreisswitz.caju.model.authorizer

import com.jmreisswitz.caju.model.account.Account
import com.jmreisswitz.caju.model.transaction.Transaction
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

class TransactionAuthorizerWithFallbackTest {

    private val defaultAuthorizer: TransactionAuthorizer = mock<TransactionAuthorizer>()
    private val fallbackAuthorizer: TransactionAuthorizer = mock<TransactionAuthorizer>()
    private val authorizer: TransactionAuthorizerWithFallback =
        TransactionAuthorizerWithFallback(defaultAuthorizer, fallbackAuthorizer)

    @Test
    fun `should authorize with default authorizer when approved`() {
        val transaction = mock<Transaction>()
        val account = mock<Account>()
        val approvedResult = TransactionAuthorizerResult(AuthorizationStatus.APPROVED)
        `when`(defaultAuthorizer.authorize(transaction, account)).thenReturn(approvedResult)

        val result = authorizer.authorize(transaction, account)

        assert(result == result)
        verify(fallbackAuthorizer, times(0)).authorize(transaction, account)
    }

    @Test
    fun `should authorize with fallback authorizer when denied`() {
        val transaction = mock<Transaction>()
        val account = mock<Account>()
        val deniedResult = TransactionAuthorizerResult(AuthorizationStatus.DENIED)
        `when`(defaultAuthorizer.authorize(transaction, account)).thenReturn(deniedResult)
        val approvedResult = TransactionAuthorizerResult(AuthorizationStatus.APPROVED)
        `when`(fallbackAuthorizer.authorize(transaction, account)).thenReturn(approvedResult)

        val result = authorizer.authorize(transaction, account)

        assert(result == approvedResult)
    }

}