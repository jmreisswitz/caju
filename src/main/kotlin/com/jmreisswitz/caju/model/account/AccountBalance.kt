package com.jmreisswitz.caju.model.account

import com.jmreisswitz.caju.model.transaction.AccountTransaction

class AccountBalance(private val balanceMap: MutableMap<BalanceType, Int>) {
    fun balanceFor(balanceType: BalanceType): Int {
        return balanceMap[balanceType] ?: 0
    }

    fun subtractBalance(balanceType: BalanceType, amount: Int): AccountBalance {
        require(hasBalanceFor(balanceType, amount)) { throw NotEnoughBalanceException() }
        balanceMap[balanceType] = balanceFor(balanceType) - amount
        return AccountBalance(balanceMap)
    }

    fun subtractBalance(accountTransaction: AccountTransaction): AccountBalance {
        return subtractBalance(accountTransaction.balanceType, accountTransaction.totalAmount)
    }

    fun hasBalanceFor(balanceType: BalanceType, amount: Int): Boolean {
        return balanceFor(balanceType) >= amount
    }

    fun hasBalanceFor(accountTransaction: AccountTransaction): Boolean {
        return hasBalanceFor(accountTransaction.balanceType, accountTransaction.totalAmount)
    }

    fun subtractFrom(accountTransaction: AccountTransaction) {
        subtractBalance(accountTransaction.balanceType, accountTransaction.totalAmount)
    }
}