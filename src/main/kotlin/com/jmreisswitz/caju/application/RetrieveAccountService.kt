package com.jmreisswitz.caju.application

import com.jmreisswitz.caju.model.account.AccountId
import com.jmreisswitz.caju.model.account.AccountRepository
import org.springframework.stereotype.Service

@Service
class RetrieveAccountService(
    private val accountRepository: AccountRepository
) {

    fun all() = accountRepository.all()

    fun findById(id: AccountId) = accountRepository.findById(id)

}