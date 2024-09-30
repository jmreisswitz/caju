package com.jmreisswitz.caju.infrastructure.persistence

import com.jmreisswitz.caju.model.account.Account
import com.jmreisswitz.caju.model.account.AccountId
import com.jmreisswitz.caju.model.account.AccountRepository
import java.util.*

class InMemoryAccountRepository(
    private val accounts: MutableMap<AccountId, Account> = mutableMapOf()
) : AccountRepository {

    override fun save(account: Account): Account {
        accounts[account.id] = account
        return account
    }

    override fun findById(id: AccountId): Optional<Account> {
        return Optional.ofNullable(accounts[id])
    }
}
