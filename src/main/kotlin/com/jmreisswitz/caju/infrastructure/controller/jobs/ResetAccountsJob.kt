package com.jmreisswitz.caju.infrastructure.controller.jobs

import com.jmreisswitz.caju.infrastructure.config.AccountConfig
import com.jmreisswitz.caju.model.account.AccountRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component


@Component
class ResetAccountsJob(
    private val accountRepository: AccountRepository
) {

    private val logger: Logger = LoggerFactory.getLogger(ResetAccountsJob::class.java)

    @Scheduled(cron = "0 0 * * * *")
    fun resetAccounts() {
        logger.info("Resetting accounts")
        val defaultAccountsMap = AccountConfig.defaultAccountsMapCopy()
        for (account in defaultAccountsMap.values) {
            accountRepository.save(account)
        }
    }

}
