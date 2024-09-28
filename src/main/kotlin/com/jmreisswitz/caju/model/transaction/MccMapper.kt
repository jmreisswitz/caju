package com.jmreisswitz.caju.model.transaction

import com.jmreisswitz.caju.model.account.BalanceType

class MccMapper {

    fun mapToBalanceType(mcc: Mcc): BalanceType {
        return when (mcc.code) {
            "5411", "5412" -> BalanceType.FOOD
            "5811", "5812" -> BalanceType.MEAL
            else -> BalanceType.CASH
        }
    }

}