package com.jmreisswitz.caju.infrastructure.controller

import com.jmreisswitz.caju.application.TransactionService
import com.jmreisswitz.caju.infrastructure.controller.requests.TransactionRequest
import com.jmreisswitz.caju.model.authorizer.AuthorizationStatus
import com.jmreisswitz.caju.model.authorizer.TransactionAuthorizerResult
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(TransactionController::class)
class TransactionControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var transactionService: TransactionService

    @Test
    fun `should return 200 and code 00 when transaction is authorized`() {
        val transactionRequest = TransactionRequest(
            "123456",
            100.0,
            "1234",
            "Caju"
        )
        val transactionResult = TransactionAuthorizerResult(AuthorizationStatus.APPROVED)
         `when`(transactionService.authorize(transactionRequest.asTransactionModel())).thenReturn(transactionResult)

        val result = mockMvc.perform(
            MockMvcRequestBuilders.post("/transaction/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {
                        "accountId": "123456",
                        "totalAmount": 100.0,
                        "mcc": "1234",
                        "merchant": "Caju"
                    }
                """.trimIndent())
        )

        result.andExpect(status().isOk)
            .andExpect(content().json(
                """
                {
                    "code": "00"
                }
                """.trimIndent()
            ))
    }


}