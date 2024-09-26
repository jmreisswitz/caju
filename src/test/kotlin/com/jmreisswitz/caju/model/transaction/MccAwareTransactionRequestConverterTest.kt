package com.jmreisswitz.caju.model.transaction

import com.jmreisswitz.caju.model.account.AccountId
import com.jmreisswitz.caju.model.account.BalanceType
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import kotlin.test.Test

class MccAwareTransactionRequestConverterTest {

    private val mccTransactionMapper = mock<MccTransactionMapper>()
    private val mccAwareTransactionRequestConverter =
        MccAwareTransactionRequestConverter(mccTransactionMapper)

    @Test
    fun `should convert transaction request to transaction`() {
        val mcc = "5812"
        val transactionRequest = TransactionRequest(
            accountId = "1",
            totalAmount = 100,
            mcc = mcc,
            merchant = "Caju"
        )
        whenever(mccTransactionMapper.map(mcc)).thenReturn(BalanceType.MEAL)

        val transaction = mccAwareTransactionRequestConverter.convert(transactionRequest)

        assert(transaction.accountId == AccountId("1"))
        assert(transaction.totalAmount == 100)
        assert(transaction.balanceType == BalanceType.MEAL)
        assert(transaction.merchant == "Caju")
    }



}