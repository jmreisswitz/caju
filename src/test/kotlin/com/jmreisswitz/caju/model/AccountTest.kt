package com.jmreisswitz.caju.model

import com.jmreisswitz.caju.model.account.*
import com.jmreisswitz.caju.model.transaction.AccountTransaction
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows


class AccountTest {

    @Test
    fun `performs transaction with positive amount`() {
        val account = AccountFixture(foodBalance = 1000).build()
        val accountTransaction = AccountTransaction(100, BalanceType.FOOD)
        account.execute(accountTransaction)
        assert(account.balance.balanceFor(BalanceType.FOOD) == 900)
    }

    @Test
    fun `performs transaction with negative amount`() {
        val account = AccountFixture().build()
        val accountTransaction = AccountTransaction(-100, BalanceType.FOOD)

        assertThrows<IllegalArgumentException> {
            account.execute(accountTransaction)
        }

    }

    @Test
    fun `performs transaction with zero amount`() {
        val account = AccountFixture(foodBalance = 100).build()
        val accountTransaction = AccountTransaction(101, BalanceType.FOOD)

        assertThrows<NotEnoughBalanceException> {
            account.execute(accountTransaction)
        }
    }

    @Test
    fun `performs transaction with amount equal to balance`() {
        val account = AccountFixture(foodBalance = 100).build()
        val accountTransaction = AccountTransaction(100, BalanceType.FOOD)
        account.execute(accountTransaction)
        assert(account.balance.balanceFor(BalanceType.FOOD) == 0)
    }

}