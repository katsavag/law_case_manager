package com.katsadourose.lawcasemanager.lawfirm.service

import com.katsadourose.lawcasemanager.lawfirm.dto.CreateLawFirmUserRequest
import com.katsadourose.lawcasemanager.lawfirm.model.LawFirmUser
import java.util.*

interface LawFirmUserService {
    fun createUser(request: CreateLawFirmUserRequest): LawFirmUser
    fun findById(userId: UUID): LawFirmUser
    fun findUsersByLawFirm(lawFirmId: UUID): List<LawFirmUser>
    fun updateBasicInfo(userId: UUID, firstName: String, lastName: String): LawFirmUser
}