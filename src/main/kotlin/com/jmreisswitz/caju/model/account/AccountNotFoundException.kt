package com.jmreisswitz.caju.model.account

class AccountNotFoundException(accountId: AccountId):
    RuntimeException("Account not found with id: $accountId") {

}
