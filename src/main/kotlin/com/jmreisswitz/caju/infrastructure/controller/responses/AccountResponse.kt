package com.jmreisswitz.caju.infrastructure.controller.responses

import com.jmreisswitz.caju.model.account.Account
import com.jmreisswitz.caju.model.account.BalanceType

data class AccountResponse(
    val id: String,
    val name: String,
    val balances: Map<String, Double>
) {
    companion object {
        fun from(account: Account): AccountResponse {
            return AccountResponse(
                account.id.toString(),
                account.name,
                balancesFrom(account)
            )
        }

        private fun balancesFrom(account: Account): Map<String, Double> {
            return enumValues<BalanceType>()
                .associate { it.name to account.balance.balanceFor(it) / 100.0 }
        }
    }

}
