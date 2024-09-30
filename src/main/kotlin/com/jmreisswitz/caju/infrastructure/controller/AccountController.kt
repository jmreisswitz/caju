package com.jmreisswitz.caju.infrastructure.controller

import com.jmreisswitz.caju.application.RetrieveAccountService
import com.jmreisswitz.caju.infrastructure.controller.responses.AccountResponse
import com.jmreisswitz.caju.model.account.AccountId
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/account")
class AccountController(
    private val retrieveAccountService: RetrieveAccountService
) {

    @GetMapping("/")
    fun retrieveAllAccounts(): ResponseEntity<List<AccountResponse>> {
        val accounts = retrieveAccountService.all()
        val response = accounts.map { AccountResponse.from(it) }
        return ResponseEntity.ok(response)
    }

    @GetMapping("/{id}")
    fun retrieveAccountById(@PathVariable id: String): ResponseEntity<AccountResponse> {
        val optionalAccount = retrieveAccountService.findById(AccountId(id))
        if (optionalAccount.isEmpty) {
            return ResponseEntity.notFound().build()
        }
        val response = AccountResponse.from(optionalAccount.get())
        return ResponseEntity.ok(response)
    }
}