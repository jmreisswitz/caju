package com.jmreisswitz.caju.infrastructure.config

import com.jmreisswitz.caju.infrastructure.persistence.InMemoryAccountRepository
import com.jmreisswitz.caju.model.account.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AccountConfig {

    @Bean
    fun accountRepository(): AccountRepository {
        return InMemoryAccountRepository(accounts = defaultAccountsMap())
    }

    fun defaultAccountsMap(): MutableMap<AccountId, Account> {
        val accounts = listOf(
            Account(AccountId("1"), "100 em todos", AccountBalance(
                mutableMapOf(
                    BalanceType.FOOD to 10000,
                    BalanceType.MEAL to 10000,
                    BalanceType.CASH to 10000
                )
            )),
            Account(AccountId("2"), "so meal", AccountBalance(
                mutableMapOf(
                    BalanceType.MEAL to 10000,
                )
            )),
            Account(AccountId("3"), "so cash", AccountBalance(
                mutableMapOf(
                    BalanceType.CASH to 10000,
                )
            )),
            Account(AccountId("4"), "Ricão", AccountBalance(
                mutableMapOf(
                    BalanceType.FOOD to 9999999,
                    BalanceType.MEAL to 9999999,
                    BalanceType.CASH to 9999999
                )
            ))
        )
        return accounts.associateBy { it.id }.toMutableMap()
    }

    companion object {
        fun defaultAccountsMapCopy() = AccountConfig().defaultAccountsMap()
    }
}
