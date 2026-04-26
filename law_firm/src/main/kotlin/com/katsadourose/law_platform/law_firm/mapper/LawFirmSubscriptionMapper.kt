package com.katsadourose.law_platform.law_firm.mapper

import com.katsadourose.law_platform.law_firm.model.LawFirmSubscription
import java.util.UUID

object LawFirmSubscriptionMapper {
    fun LawFirmSubscription.toEntity(lawFirmId: UUID): LawFirmSubscription {
        return this.copy(lawFirmId = lawFirmId)
    }
}
