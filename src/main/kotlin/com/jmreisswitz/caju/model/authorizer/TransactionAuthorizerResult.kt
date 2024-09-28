package com.jmreisswitz.caju.model.authorizer

class TransactionAuthorizerResult(
    val authorizationStatus: AuthorizationStatus
){
}

enum class AuthorizationStatus {
    APPROVED,
    DENIED,
    ERROR
}
