package com.katsadourose.lawcasemanager.web.api

import com.katsadourose.lawcasemanager.lawfirm.dto.RecordPaymentRequest
import com.katsadourose.lawcasemanager.lawfirm.dto.LawFirmPaymentResponse
import com.katsadourose.lawcasemanager.lawfirm.service.LawFirmPaymentService
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@RequestMapping("/api/admin/law-firms/{lawFirmId}/payments")
class LawFirmPaymentApiController(
    private val lawFirmPaymentService: LawFirmPaymentService
) {

    private val logger = LoggerFactory.getLogger(LawFirmPaymentApiController::class.java)

    @PostMapping
    fun recordPayment(
        @PathVariable lawFirmId: UUID,
        @RequestBody request: RecordPaymentRequest
    ): ResponseEntity<LawFirmPaymentResponse> {
        logger.info("Received request to record payment for law firm with id: $lawFirmId, amount: ${request.amount}")
        val payment = lawFirmPaymentService.recordPayment(request)
        return ResponseEntity.status(HttpStatus.CREATED).body(payment)
    }

    @GetMapping
    fun findAllByLawFirmId(@PathVariable lawFirmId: UUID): ResponseEntity<List<LawFirmPaymentResponse>> {
        logger.info("Received request to find all payments for law firm with id: $lawFirmId")
        val payments = lawFirmPaymentService.findAllByLawFirmId(lawFirmId)
        return ResponseEntity.ok(payments)
    }
}
