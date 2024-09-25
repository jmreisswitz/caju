package com.jmreisswitz.caju.model

class Account(
    val id: AccountId,
    val name: String,
    var balance: Int
    ) {

    fun execute(transaction: Transaction) {
        require(transaction.totalAmount > 0) { "Transaction total amount must be greater than 0" }
        require(transaction.accountId == id) { "Transaction account id does not match account id" }
        require(balance >= transaction.totalAmount) { "Insufficient funds" }
        balance -= transaction.totalAmount
    }

}