package com.jmreisswitz.caju.infrastructure.controller

import com.jmreisswitz.caju.application.TransactionServiceWithLock
import com.jmreisswitz.caju.infrastructure.controller.requests.TransactionRequest
import com.jmreisswitz.caju.infrastructure.controller.responses.TransactionResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/transaction")
class TransactionController(
    private val transactionService: TransactionServiceWithLock
) {

    @PostMapping("/")
    fun authorizeTransaction(@RequestBody transactionRequest: TransactionRequest):
            ResponseEntity<TransactionResponse> {
        val transactionResult = transactionService.authorize(transactionRequest.asTransactionModel())

        val response = TransactionResponse.from(transactionResult)
        return ResponseEntity.ok(response)
    }

}