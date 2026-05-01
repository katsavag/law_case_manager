package com.katsadourose.lawcasemanager.lawfirm.mapper

import com.katsadourose.lawcasemanager.lawfirm.model.LawFirmSubscription
import com.katsadourose.lawcasemanager.lawfirm.dto.LawFirmSubscriptionResponse

object LawFirmSubscriptionMapper {

    fun LawFirmSubscription.toResponse(): LawFirmSubscriptionResponse {
        return LawFirmSubscriptionResponse(
            id = this.id,
            lawFirmId = this.lawFirm.id,
            status = this.status.name,
            plan = this.plan.name,
            currentPeriodStart = this.currentPeriodStart,
            currentPeriodEnd = this.currentPeriodEnd,
            updatedAt = this.updatedAt
        )
    }
}
