package com.jmreisswitz.caju.model.account

class AccountFixture(
    private val accountId: AccountId = AccountId("123"),
    private val name: String = "Account Name",
    private val foodBalance: Int = 0,
    private val cashBalance: Int = 0,
    private val mealBalance: Int = 0

) {
    fun build(): Account {
        val balance = AccountBalance(
            mutableMapOf(
                Pair(BalanceType.FOOD, foodBalance),
                Pair(BalanceType.CASH, cashBalance),
                Pair(BalanceType.MEAL, mealBalance)
            )
        )
        return Account(
            id = accountId,
            name = name,
            balance = balance
        )
    }


}