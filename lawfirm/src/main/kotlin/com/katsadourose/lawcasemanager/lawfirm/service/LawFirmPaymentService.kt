package com.katsadourose.lawcasemanager.lawfirm.service

import com.katsadourose.lawcasemanager.lawfirm.dto.RecordPaymentRequest
import com.katsadourose.lawcasemanager.lawfirm.dto.LawFirmPaymentResponse
import java.util.UUID

interface LawFirmPaymentService {
    fun recordPayment(request: RecordPaymentRequest): LawFirmPaymentResponse
    fun findAllByLawFirmId(lawFirmId: UUID): List<LawFirmPaymentResponse>
}
