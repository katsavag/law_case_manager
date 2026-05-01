package com.katsadourose.lawcasemanager.lawfirm.dto

import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID

data class LawFirmResponse(
    val id: UUID?,
    val name: String,
    val taxNumber: String,
    val address: String,
    val email: String,
    val phone: String,
    val active: Boolean,
    val subscriptionStatus: String,
    val subscriptionPlan: String,
    val createdAt: LocalDateTime?,
)

data class LawFirmPaymentResponse(
    val id: UUID?,
    val lawFirmId: UUID,
    val amount: BigDecimal,
    val currency: String,
    val paidAt: LocalDateTime,
    val periodStart: LocalDateTime,
    val periodEnd: LocalDateTime,
    val notes: String?,
    val recordedBy: UUID,
    val createdAt: LocalDateTime
)

data class LawFirmSubscriptionResponse(
    val id: UUID?,
    val lawFirmId: UUID?,
    val status: String,
    val plan: String,
    val currentPeriodStart: LocalDateTime,
    val currentPeriodEnd: LocalDateTime,
    val updatedAt: LocalDateTime
)
