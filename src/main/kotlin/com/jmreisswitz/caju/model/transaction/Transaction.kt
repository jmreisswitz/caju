package com.jmreisswitz.caju.model.transaction

import com.jmreisswitz.caju.model.account.AccountId
import com.jmreisswitz.caju.model.account.BalanceType

class Transaction(val accountId: AccountId,
                  val totalAmount: Int,
                  val balanceType: BalanceType,
                  val merchant: String
    ) {



}