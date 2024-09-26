package com.jmreisswitz.caju.model.transaction

import com.jmreisswitz.caju.model.account.BalanceType

class MccTransactionMapper {

    fun map(mcc: String): BalanceType {
        return when (mcc) {
            "5411", "5412" -> BalanceType.FOOD
            "5811", "5812" -> BalanceType.MEAL
            else -> BalanceType.CASH
        }
    }

}