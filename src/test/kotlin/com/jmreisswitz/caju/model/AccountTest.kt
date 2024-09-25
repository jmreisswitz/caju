package com.jmreisswitz.caju.model

import com.jmreisswitz.caju.model.account.*
import com.jmreisswitz.caju.model.transaction.Transaction
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows


class AccountTest {

    @Test
    fun performSimpleTransaction() {
        val account = AccountFixture(foodBalance = 1000).build()
        val transaction = Transaction(account.id, 100, BalanceType.FOOD, "Caju")
        account.execute(transaction)
        assert(account.balance.balanceFor(BalanceType.FOOD) == 900)
    }

    @Test
    fun performTransactionWithNegativeAmount() {
        val account = AccountFixture().build()
        val transaction = Transaction(account.id, -100, BalanceType.FOOD, "Caju")

        assertThrows<IllegalArgumentException> {
            account.execute(transaction)
        }

    }

    @Test
    fun performTransactionWithDifferentAccountId() {
        val account = AccountFixture(accountId = AccountId("1")).build()
        val transaction = Transaction(AccountId("2"), 100, BalanceType.FOOD, "Caju")

        assertThrows<IllegalArgumentException> {
            account.execute(transaction)
        }
    }

    @Test
    fun performTransactionWithInsufficientFunds() {
        val account = AccountFixture(foodBalance = 100).build()
        val transaction = Transaction(account.id, 101, BalanceType.FOOD, "Caju")

        assertThrows<IllegalArgumentException> {
            account.execute(transaction)
        }
    }

    @Test
    fun performTransactionThatResultsOnZeroBalance() {
        val account = AccountFixture(foodBalance = 100).build()
        val transaction = Transaction(account.id, 100, BalanceType.FOOD, "Caju")
        account.execute(transaction)
        assert(account.balance.balanceFor(BalanceType.FOOD) == 0)
    }

}