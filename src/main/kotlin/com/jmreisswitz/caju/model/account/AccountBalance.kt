package com.jmreisswitz.caju.model.account

import com.jmreisswitz.caju.model.transaction.Transaction

class AccountBalance(private val balanceMap: MutableMap<BalanceType, Int>) {
    fun balanceFor(balanceType: BalanceType): Int {
        return balanceMap[balanceType] ?: 0
    }

    fun addBalance(balanceType: BalanceType, amount: Int): AccountBalance {
        balanceMap[balanceType] = balanceFor(balanceType) + amount
        return AccountBalance(balanceMap)
    }

    fun subtractBalance(balanceType: BalanceType, amount: Int): AccountBalance {
        require(hasBalance(balanceType, amount)) { "Insufficient funds" }
        balanceMap[balanceType] = balanceFor(balanceType) - amount
        return AccountBalance(balanceMap)
    }

    fun hasBalance(balanceType: BalanceType, amount: Int): Boolean {
        return balanceFor(balanceType) >= amount
    }

    fun subtractFrom(transaction: Transaction) {
        subtractBalance(transaction.balanceType, transaction.totalAmount)
    }
}