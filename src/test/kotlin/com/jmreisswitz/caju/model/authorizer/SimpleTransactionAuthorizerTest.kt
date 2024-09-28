package com.jmreisswitz.caju.model.authorizer

import com.jmreisswitz.caju.model.account.AccountFixture
import com.jmreisswitz.caju.model.account.BalanceType
import com.jmreisswitz.caju.model.transaction.Mcc
import com.jmreisswitz.caju.model.transaction.TransactionFixture
import com.jmreisswitz.caju.model.transaction.TransactionToBalanceTypeConverter
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class SimpleTransactionAuthorizerTest {

    private val converter = mock<TransactionToBalanceTypeConverter>()
    private val simpleTransactionAuthorizer = SimpleTransactionAuthorizer(converter)

    @Test
    fun `should authorize transaction if enough balance`() {
        val account = AccountFixture(foodBalance = 100).build()
        val mcc = Mcc("5411")
        val transaction = TransactionFixture(mcc = mcc, totalAmount = 50).build()
        `when`(converter.convert(transaction)).thenReturn(BalanceType.FOOD)

        val result = simpleTransactionAuthorizer.authorize(transaction, account)

        assert(result.authorizationStatus == AuthorizationStatus.APPROVED)
    }

    @Test
    fun `should authorize transaction if exactly value of balance`() {
        val account = AccountFixture(foodBalance = 100).build()
        val mcc = Mcc("5411")
        val transaction = TransactionFixture(mcc = mcc, totalAmount = 100).build()
        `when`(converter.convert(transaction)).thenReturn(BalanceType.FOOD)

        val result = simpleTransactionAuthorizer.authorize(transaction, account)

        assert(result.authorizationStatus == AuthorizationStatus.APPROVED)
    }

    @Test
    fun `should not authorize transaction if not enough balance`() {
        val account = AccountFixture(foodBalance = 100).build()
        val mcc = Mcc("5411")
        val transaction = TransactionFixture(mcc = mcc, totalAmount = 150).build()
        `when`(converter.convert(transaction)).thenReturn(BalanceType.FOOD)

        val result = simpleTransactionAuthorizer.authorize(transaction, account)

        assert(result.authorizationStatus == AuthorizationStatus.DENIED)
    }

}