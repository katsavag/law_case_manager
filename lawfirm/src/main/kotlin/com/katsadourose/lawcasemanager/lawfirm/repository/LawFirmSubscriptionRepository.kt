package com.katsadourose.lawcasemanager.lawfirm.repository

import com.katsadourose.lawcasemanager.lawfirm.model.LawFirmSubscription
import com.katsadourose.lawcasemanager.lawfirm.model.SubscriptionStatus
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.time.LocalDateTime
import java.util.UUID

interface LawFirmSubscriptionRepository : JpaRepository<LawFirmSubscription, UUID> {
    @Query("SELECT s FROM LawFirmSubscription s WHERE s.lawFirm.id = :lawFirmId")
    fun findByLawFirm_Id(@Param("lawFirmId") lawFirmId: UUID): LawFirmSubscription?
    fun findAllByStatus(status: SubscriptionStatus): List<LawFirmSubscription>
    fun findAllByCurrentPeriodEndBefore(date: LocalDateTime): List<LawFirmSubscription>
}
