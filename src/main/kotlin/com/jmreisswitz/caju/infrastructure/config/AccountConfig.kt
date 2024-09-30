package com.jmreisswitz.caju.infrastructure.config

import com.jmreisswitz.caju.infrastructure.persistence.InMemoryAccountRepository
import com.jmreisswitz.caju.model.account.AccountRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AccountConfig {

    @Bean
    fun accountRepository(): AccountRepository {
        return InMemoryAccountRepository()
    }

}