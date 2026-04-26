package com.katsadourose.law_platform.law_firm.service

import com.katsadourose.law_platform.law_firm.model.LawFirmPayment
import com.katsadourose.law_platform.law_firm.dto.RecordPaymentRequest
import java.util.UUID

interface LawFirmPaymentService {
    fun recordPayment(request: RecordPaymentRequest): LawFirmPayment
    fun findAllByLawFirmId(lawFirmId: UUID): List<LawFirmPayment>
}
