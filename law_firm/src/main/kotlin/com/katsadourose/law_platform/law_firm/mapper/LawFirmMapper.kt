package com.katsadourose.law_platform.law_firm.mapper

import com.katsadourose.law_platform.law_firm.model.LawFirm
import com.katsadourose.law_platform.law_firm.dto.OnboardLawFirmRequest

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
}
