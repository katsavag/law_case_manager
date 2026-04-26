package com.katsadourose.law_platform.law_firm.service

import com.katsadourose.law_platform.law_firm.model.LawFirm
import com.katsadourose.law_platform.law_firm.dto.OnboardLawFirmRequest
import java.util.UUID

interface LawFirmService {
    fun onboard(request: OnboardLawFirmRequest): LawFirm
    fun suspend(lawFirmId: UUID)
    fun activate(lawFirmId: UUID)
    fun findById(lawFirmId: UUID): LawFirm
    fun findAll(): List<LawFirm>
}
