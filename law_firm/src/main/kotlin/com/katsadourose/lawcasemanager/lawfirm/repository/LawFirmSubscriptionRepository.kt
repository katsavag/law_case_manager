package com.katsadourose.lawcasemanager.lawfirm.repository

import com.katsadourose.lawcasemanager.lawfirm.model.LawFirmSubscription
import com.katsadourose.lawcasemanager.lawfirm.model.SubscriptionStatus
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDateTime
import java.util.UUID

interface LawFirmSubscriptionRepository : JpaRepository<LawFirmSubscription, UUID> {
    fun findByLawFirmId(lawFirmId: UUID): LawFirmSubscription?
    fun findAllByStatus(status: SubscriptionStatus): List<LawFirmSubscription>
    fun findAllByCurrentPeriodEndBefore(date: LocalDateTime): List<LawFirmSubscription>
}
