package com.jmreisswitz.caju.infrastructure.config

import com.jmreisswitz.caju.model.account.BalanceType
import com.jmreisswitz.caju.model.authorizer.CashBalanceFallbackTransactionAuthorizer
import com.jmreisswitz.caju.model.authorizer.SimpleTransactionAuthorizer
import com.jmreisswitz.caju.model.authorizer.TransactionAuthorizer
import com.jmreisswitz.caju.model.authorizer.TransactionAuthorizerWithFallback
import com.jmreisswitz.caju.model.transaction.*
import com.jmreisswitz.caju.model.transaction.converter.ComposedTransactionToBalanceTypeConverter
import com.jmreisswitz.caju.model.transaction.converter.MccTransactionToBalanceTypeConverter
import com.jmreisswitz.caju.model.transaction.converter.MerchantTransactionToBalanceTypeConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class TransactionConfig {

    @Bean
    fun transactionAuthorizer(): TransactionAuthorizer {
        return TransactionAuthorizerWithFallback(
            SimpleTransactionAuthorizer(transactionToBalanceTypeConverter()),
            CashBalanceFallbackTransactionAuthorizer()
        )
    }

    @Bean
    fun transactionToBalanceTypeConverter(): TransactionToBalanceTypeConverter {
        return ComposedTransactionToBalanceTypeConverter(
            listOf(
                MerchantTransactionToBalanceTypeConverter(merchantMapper()),
                MccTransactionToBalanceTypeConverter(mccMapper())
            )
        )
    }

    @Bean
    fun merchantMapper(): MerchantMapper {
        return MerchantMapper(
            SimpleMerchantNameExtractor(),
            mapOf(
                MerchantName("Amazon") to BalanceType.CASH,
                MerchantName("Padaria do Ze") to BalanceType.FOOD,
                MerchantName("Rabbibs") to BalanceType.MEAL,
                MerchantName("Uber") to BalanceType.CASH,
                MerchantName("Ifood") to BalanceType.MEAL
            )
        )
    }

    @Bean
    fun mccMapper(): MccMapper {
        return MccMapper()
    }

}