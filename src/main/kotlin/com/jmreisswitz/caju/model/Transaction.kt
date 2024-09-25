package com.jmreisswitz.caju.model

class Transaction(val accountId: AccountId,
                  val totalAmount: Int,
                  val mcc: String,
                  val merchant: String
    ) {



}