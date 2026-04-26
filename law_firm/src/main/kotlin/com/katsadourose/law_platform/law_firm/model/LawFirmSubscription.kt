package com.katsadourose.law_platform.law_firm.model

import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "law_firm_subscriptions")
data class LawFirmSubscription(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,

    @Column(name = "law_firm_id", nullable = false, unique = true)
    val lawFirmId: UUID,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val status: SubscriptionStatus = SubscriptionStatus.ACTIVE,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val plan: SubscriptionPlan = SubscriptionPlan.STARTER,

    @Column(name = "current_period_start", nullable = false)
    val currentPeriodStart: LocalDateTime,

    @Column(name = "current_period_end", nullable = false)
    val currentPeriodEnd: LocalDateTime,

    @Column(name = "updated_at", nullable = false)
    val updatedAt: LocalDateTime = LocalDateTime.now()
)

enum class SubscriptionStatus {
    ACTIVE, EXPIRED, SUSPENDED
}

enum class SubscriptionPlan {
    STARTER, PRO
}
