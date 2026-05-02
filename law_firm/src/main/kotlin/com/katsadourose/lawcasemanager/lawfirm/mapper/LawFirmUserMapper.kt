package com.katsadourose.lawcasemanager.lawfirm.mapper

import com.katsadourose.lawcasemanager.lawfirm.dto.CreateLawFirmUserRequest
import com.katsadourose.lawcasemanager.lawfirm.model.LawFirm
import com.katsadourose.lawcasemanager.lawfirm.model.LawFirmUser
import com.katsadourose.lawcasemanager.lawfirm.model.UserRole
import java.time.LocalDateTime

object LawFirmUserMapper {
    fun CreateLawFirmUserRequest.toEntity(lawFirm: LawFirm): LawFirmUser {
        return LawFirmUser(
            lawFirm = lawFirm,
            firstName = this.firstName,
            lastName = this.lastName,
            email = this.email,
            passwordHash = this.passwordHash,
            role = UserRole.valueOf(this.role),
            active = true,
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now(),
            barNumber = this.barNumber,
            barAssociation = this.barAssociation,
            specialization = this.specialization
        )
    }
}
