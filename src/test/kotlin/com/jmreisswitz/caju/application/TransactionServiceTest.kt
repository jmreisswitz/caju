package com.jmreisswitz.caju.application

import com.jmreisswitz.caju.model.account.Account
import com.jmreisswitz.caju.model.account.AccountId
import com.jmreisswitz.caju.model.account.AccountNotFoundException
import com.jmreisswitz.caju.model.account.AccountRepository
import com.jmreisswitz.caju.model.authorizer.AuthorizationStatus
import com.jmreisswitz.caju.model.authorizer.TransactionAuthorizer
import com.jmreisswitz.caju.model.authorizer.TransactionAuthorizerResult
import com.jmreisswitz.caju.model.transaction.Transaction
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import java.util.*
import kotlin.test.Test
import kotlin.test.assertFailsWith

class TransactionServiceTest {

    private val accountRepository = mock<AccountRepository>()
    private val transactionAuthorizer = mock<TransactionAuthorizer>()
    private val service = TransactionService(accountRepository, transactionAuthorizer)

    @Test
    fun `save account when transaction is authorized`() {
        val transaction = mock<Transaction>()
        val account = mock<Account>()
        val accountId = mock<AccountId>()
        `when`(transaction.accountId).thenReturn(accountId)
        `when`(accountRepository.findById(accountId)).thenReturn(Optional.of(account))
        val transactionResult =
            TransactionAuthorizerResult(AuthorizationStatus.APPROVED)
        `when`(transactionAuthorizer.authorize(transaction, account)).thenReturn(transactionResult)

        val result = service.authorizeTransaction(transaction)

        assert(result == transactionResult)
        verify(accountRepository).save(account)
    }

    @Test
    fun `throw account not found exception when account is not found`() {
        val transaction = mock<Transaction>()
        val accountId = mock<AccountId>()
        `when`(transaction.accountId).thenReturn(accountId)
        `when`(accountRepository.findById(accountId)).thenReturn(Optional.empty())

        assertFailsWith<AccountNotFoundException> {
            service.authorizeTransaction(transaction)
        }
    }

    @Test
    fun `when transaction is not approved, account is not saved`() {
        val transaction = mock<Transaction>()
        val account = mock<Account>()
        val accountId = mock<AccountId>()
        `when`(transaction.accountId).thenReturn(accountId)
        `when`(accountRepository.findById(accountId)).thenReturn(Optional.of(account))
        val transactionResult =
            TransactionAuthorizerResult(AuthorizationStatus.DENIED)
        `when`(transactionAuthorizer.authorize(transaction, account)).thenReturn(transactionResult)

        val result = service.authorizeTransaction(transaction)

        assert(result == transactionResult)
        verify(accountRepository, times(0)).save(account)
    }

}