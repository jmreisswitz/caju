package com.jmreisswitz.caju.infrastructure.controller.responses

import com.jmreisswitz.caju.model.authorizer.AuthorizationStatus
import com.jmreisswitz.caju.model.authorizer.TransactionAuthorizerResult

private const val APPROVED_CODE = "00"
private const val DENIED_CODE = "51"
private const val ERROR_CODE = "07"

data class TransactionResponse(
    val code: String
){
    companion object {
        fun from(transactionResult: TransactionAuthorizerResult): TransactionResponse {
            return when(transactionResult.authorizationStatus) {
                AuthorizationStatus.APPROVED -> TransactionResponse(APPROVED_CODE)
                AuthorizationStatus.DENIED -> TransactionResponse(DENIED_CODE)
                AuthorizationStatus.ERROR -> TransactionResponse(ERROR_CODE)
            }
        }
    }
}
