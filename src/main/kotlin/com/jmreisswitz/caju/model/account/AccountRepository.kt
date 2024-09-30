package com.jmreisswitz.caju.model.account

import java.util.*

interface AccountRepository {

    fun save(account: Account): Account
    fun findById(id: AccountId): Optional<Account>
    fun all(): List<Account>
}
