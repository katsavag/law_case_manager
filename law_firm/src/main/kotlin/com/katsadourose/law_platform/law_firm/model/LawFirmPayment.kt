package com.katsadourose.law_platform.law_firm.model

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "law_firm_payments")
data class LawFirmPayment(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,

    @Column(name = "law_firm_id", nullable = false)
    val lawFirmId: UUID,

    @Column(nullable = false)
    val amount: BigDecimal,

    @Column(nullable = false)
    val currency: String = "EUR",

    @Column(name = "paid_at", nullable = false)
    val paidAt: LocalDateTime,

    @Column(name = "period_start", nullable = false)
    val periodStart: LocalDateTime,

    @Column(name = "period_end", nullable = false)
    val periodEnd: LocalDateTime,

    val notes: String? = null,

    @Column(name = "recorded_by", nullable = false)
    val recordedBy: UUID,

    @Column(name = "created_at", nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now()
)
