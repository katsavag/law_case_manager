package com.katsadourose.law_platform.law_firm.repository

import com.katsadourose.law_platform.law_firm.model.LawFirmSubscription
import com.katsadourose.law_platform.law_firm.model.SubscriptionStatus
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDateTime
import java.util.UUID

interface LawFirmSubscriptionRepository : JpaRepository<LawFirmSubscription, UUID> {
    fun findByLawFirmId(lawFirmId: UUID): LawFirmSubscription?
    fun findAllByStatus(status: SubscriptionStatus): List<LawFirmSubscription>
    fun findAllByCurrentPeriodEndBefore(date: LocalDateTime): List<LawFirmSubscription>
}
