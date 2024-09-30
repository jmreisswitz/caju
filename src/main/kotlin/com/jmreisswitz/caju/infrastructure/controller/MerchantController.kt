package com.jmreisswitz.caju.infrastructure.controller

import com.jmreisswitz.caju.infrastructure.controller.responses.MerchantMapResponse
import com.jmreisswitz.caju.model.transaction.MerchantMapper
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/merchant")
class MerchantController(
    private val merchantMapper: MerchantMapper
) {

    @GetMapping("/")
    fun retrieveAllMerchants(): ResponseEntity<MerchantMapResponse> {
        val merchantsMap = merchantMapper.merchantsMap
        val response = MerchantMapResponse.from(merchantsMap)
        return ResponseEntity.ok(response)
    }

}