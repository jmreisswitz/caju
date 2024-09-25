package com.jmreisswitz.caju.model.account

import com.jmreisswitz.caju.model.transaction.Transaction

class Account(
    val id: AccountId,
    val name: String,
    var balance: AccountBalance,
    ) {

    fun execute(transaction: Transaction) {
        require(transaction.totalAmount > 0) { "Transaction total amount must be greater than 0" }
        require(transaction.accountId == id) { "Transaction account id does not match account id" }
        balance.subtractFrom(transaction)
    }

}