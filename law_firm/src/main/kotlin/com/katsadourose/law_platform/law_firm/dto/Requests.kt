package com.katsadourose.law_platform.law_firm.dto

import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID

data class OnboardLawFirmRequest(
    val name: String,
    val taxNumber: String,
    val address: String,
    val email: String,
    val phone: String
)

data class RecordPaymentRequest(
    val lawFirmId: UUID,
    val amount: BigDecimal,
    val currency: String,
    val paidAt: LocalDateTime,
    val periodStart: LocalDateTime,
    val periodEnd: LocalDateTime,
    val notes: String?,
    val recordedBy: UUID
)
