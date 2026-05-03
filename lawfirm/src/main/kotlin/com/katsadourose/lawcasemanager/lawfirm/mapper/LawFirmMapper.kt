package com.katsadourose.lawcasemanager.lawfirm.mapper

import com.katsadourose.lawcasemanager.lawfirm.model.LawFirm
import com.katsadourose.lawcasemanager.lawfirm.dto.OnboardLawFirmRequest
import com.katsadourose.lawcasemanager.lawfirm.dto.LawFirmResponse

object LawFirmMapper {
    fun OnboardLawFirmRequest.toEntity(): LawFirm {
        return LawFirm(
            name = this.name,
            taxNumber = this.taxNumber,
            address = this.address,
            email = this.email,
            phone = this.phone
        )
    }

    fun LawFirm.toResponse(): LawFirmResponse {
        return LawFirmResponse(
            id = this.id,
            name = this.name,
            taxNumber = this.taxNumber,
            address = this.address,
            email = this.email,
            phone = this.phone,
            active = this.active,
            subscriptionStatus = this.subscription?.status?.name ?: "N/A",
            subscriptionPlan = this.subscription?.plan?.name ?: "N/A",
            createdAt = this.createdAt,
        )
    }
}
