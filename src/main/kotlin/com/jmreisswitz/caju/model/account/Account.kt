package com.jmreisswitz.caju.model.account

import com.jmreisswitz.caju.model.transaction.AccountTransaction

class Account(
    val id: AccountId,
    val name: String,
    var balance: AccountBalance,
    ) {

    fun execute(accountTransaction: AccountTransaction) {
        require(accountTransaction.totalAmount > 0) { "Transaction total amount must be greater than 0" }
        balance.subtractFrom(accountTransaction)
    }

}