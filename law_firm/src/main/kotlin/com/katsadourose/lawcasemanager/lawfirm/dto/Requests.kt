package com.katsadourose.lawcasemanager.lawfirm.dto

import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.UUID

data class OnboardLawFirmRequest(
    val name: String,
    val displayName: String,
    val taxNumber: String,
    val taxOffice: String,
    val address: String,
    val city: String,
    val country: String,
    val postalCode: String,
    val email: String,
    val phone: String,
    val notes: String?,
    val plan: String,
    val subscriptionStartsAt: LocalDate,
    val billingPeriod: String,
    val status: String,
    val sendWelcomeEmail: Boolean
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
