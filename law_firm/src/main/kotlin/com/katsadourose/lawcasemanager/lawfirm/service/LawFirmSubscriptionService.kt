package com.katsadourose.lawcasemanager.lawfirm.service

import com.katsadourose.lawcasemanager.lawfirm.model.LawFirmSubscription
import java.time.LocalDateTime
import java.util.UUID

interface LawFirmSubscriptionService {
    fun createInitialSubscription(lawFirmId: UUID): LawFirmSubscription
    fun renewSubscription(lawFirmId: UUID, periodEnd: LocalDateTime): LawFirmSubscription
    fun expireSubscription(lawFirmId: UUID): LawFirmSubscription
    fun findByLawFirmId(lawFirmId: UUID): LawFirmSubscription
}
