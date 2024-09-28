package com.jmreisswitz.caju.model.transaction

import com.jmreisswitz.caju.model.account.BalanceType

class AccountTransaction(val totalAmount: Int,
                         val balanceType: BalanceType
    ) {
}