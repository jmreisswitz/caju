package com.jmreisswitz.caju.model.account

class AccountFixture(
    val accountId: AccountId = AccountId("123"),
    val name: String = "Account Name",
    val foodBalance: Int = 0,
    val cashBalance: Int = 0,
    val mealBalance: Int = 0

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