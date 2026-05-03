package com.katsadourose.lawcasemanager.lawfirm.service

import com.katsadourose.lawcasemanager.lawfirm.dto.OnboardLawFirmRequest
import com.katsadourose.lawcasemanager.lawfirm.dto.LawFirmResponse
import java.util.UUID

interface LawFirmService {
    fun onboard(request: OnboardLawFirmRequest): LawFirmResponse
    fun suspend(lawFirmId: UUID)
    fun activate(lawFirmId: UUID)
    fun findById(lawFirmId: UUID): LawFirmResponse
    fun findAll(): List<LawFirmResponse>
}
