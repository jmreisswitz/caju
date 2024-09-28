package com.jmreisswitz.caju.model.transaction

import com.jmreisswitz.caju.model.account.AccountId
import com.jmreisswitz.caju.model.account.BalanceType
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.mockito.kotlin.verify

class MerchantAwareTransactionConverterTest {

    private val fallBackConverter: TransactionRequestConverter = mock<TransactionRequestConverter>()
    private val merchantMapper: MerchantMapper = mock<MerchantMapper>()

    private val converter: MerchantAwareTransactionConverter =
        MerchantAwareTransactionConverter(fallBackConverter, merchantMapper)

    @Test
    fun `convert to transaction with found balance type from merchant`() {
        val merchant = "Caju"
        val transactionRequest = TransactionRequestFixture(merchant = merchant).build()
        `when`(merchantMapper.mapToBalanceType(merchant)).thenReturn(BalanceType.FOOD)

        val transaction = converter.convert(transactionRequest);

        assert(transaction.accountId == AccountId("123"))
        assert(transaction.totalAmount == 100)
        assert(transaction.balanceType == BalanceType.FOOD)
        assert(transaction.merchant == merchant)
        verify(fallBackConverter, times(0)).convert(transactionRequest)
    }

    @Test
    fun `convert to transaction even when merchant map to cash`() {
        val merchant = "Some Store"
        val transactionRequest = TransactionRequestFixture(merchant = merchant).build()
        `when`(merchantMapper.mapToBalanceType(merchant)).thenReturn(BalanceType.CASH)

        val transaction = converter.convert(transactionRequest);

        assert(transaction.accountId == AccountId("123"))
        assert(transaction.totalAmount == 100)
        assert(transaction.balanceType == BalanceType.CASH)
        assert(transaction.merchant == merchant)
        verify(fallBackConverter, times(0)).convert(transactionRequest)
    }

    @Test
    fun `convert to transaction with fallback converter when merchant not found`() {
        val merchant = "Caju"
        val transactionRequest = TransactionRequestFixture(merchant = merchant).build()
        `when`(merchantMapper.mapToBalanceType(merchant)).thenThrow(MerchantNotFoundException::class.java)
        val expectedTransaction = mock<Transaction>()
        `when`(fallBackConverter.convert(transactionRequest)).thenReturn(expectedTransaction)

        val result = converter.convert(transactionRequest)

        assert(result == expectedTransaction)
    }

}