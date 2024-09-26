package com.jmreisswitz.caju.model.transaction

import com.jmreisswitz.caju.model.account.BalanceType
import org.junit.jupiter.api.Test

class MccTransactionMapperTest {

    private val mccTransactionMapper = MccTransactionMapper()

    @Test
    fun `should map mcc to food balance type`() {
        val balanceType = mccTransactionMapper.map("5411")

        assert(balanceType == BalanceType.FOOD)
    }

    @Test
    fun `should map mcc to meal balance type`() {
        val balanceType = mccTransactionMapper.map("5812")

        assert(balanceType == BalanceType.MEAL)
    }

    @Test
    fun `should map mcc to cash balance type`() {
        val balanceType = mccTransactionMapper.map("1234")

        assert(balanceType == BalanceType.CASH)
    }
}