package com.katsadourose.lawcasemanager.lawfirm.service

import com.katsadourose.lawcasemanager.lawfirm.model.LawFirm
import com.katsadourose.lawcasemanager.lawfirm.model.LawFirmSubscription
import java.time.LocalDateTime
import java.util.UUID

interface LawFirmSubscriptionService {
    fun createInitialSubscription(lawFirm: LawFirm): LawFirmSubscription
    fun renewSubscription(lawFirmId: UUID, periodEnd: LocalDateTime): LawFirmSubscription
    fun expireSubscription(lawFirmId: UUID): LawFirmSubscription
    fun findByLawFirmId(lawFirmId: UUID): LawFirmSubscription
}
