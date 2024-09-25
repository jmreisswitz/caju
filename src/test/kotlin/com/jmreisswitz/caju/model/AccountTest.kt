package com.jmreisswitz.caju.model

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows


class AccountTest {

    @Test
    fun performSimpleTransaction() {
        val account = Account(AccountId("1"), "John Doe", 1000)
        val transaction = Transaction(AccountId("1"), 100, "1234", "Amazon")
        account.execute(transaction)
        assert(account.balance == 900)
    }

    @Test
    fun performTransactionWithNegativeAmount() {
        val account = Account(AccountId("1"), "John Doe", 1000)
        val transaction = Transaction(AccountId("1"), -100, "1234", "Amazon")

        assertThrows<IllegalArgumentException> {
            account.execute(transaction)
        }

    }

    @Test
    fun performTransactionWithDifferentAccountId() {
        val account = Account(AccountId("1"), "John Doe", 1000)
        val transaction = Transaction(AccountId("2"), 100, "1234", "Amazon")

        assertThrows<IllegalArgumentException> {
            account.execute(transaction)
        }
    }

    @Test
    fun performTransactionWithInsufficientFunds() {
        val account = Account(AccountId("1"), "John Doe", 1000)
        val transaction = Transaction(AccountId("1"), 1001, "1234", "Amazon")

        assertThrows<IllegalArgumentException> {
            account.execute(transaction)
        }
    }

    @Test
    fun performTransactionThatResultsOnZeroBalance() {
        val account = Account(AccountId("1"), "John Doe", 1000)
        val transaction = Transaction(AccountId("1"), 1000, "1234", "Amazon")
        account.execute(transaction)
        assert(account.balance == 0)
    }

}