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

    private fun defaultAccountsMap(): MutableMap<AccountId, Account> {
        return mutableMapOf(
            AccountId("1") to Account(AccountId("1"), "100 em todos", AccountBalance(
                mutableMapOf(
                    BalanceType.FOOD to 10000,
                    BalanceType.MEAL to 10000,
                    BalanceType.CASH to 10000
                )
            )),
            AccountId("2") to Account(AccountId("meal"), "so meal", AccountBalance(
                mutableMapOf(
                    BalanceType.MEAL to 10000,
                )
            )),
            AccountId("3") to Account(AccountId("meal"), "so cash", AccountBalance(
                mutableMapOf(
                    BalanceType.CASH to 10000,
                )
            )),
            AccountId("4") to Account(AccountId("meal"), "Ricão", AccountBalance(
                mutableMapOf(
                    BalanceType.FOOD to 9999999,
                    BalanceType.MEAL to 9999999,
                    BalanceType.CASH to 9999999
                )
            ))
            )
    }
}
