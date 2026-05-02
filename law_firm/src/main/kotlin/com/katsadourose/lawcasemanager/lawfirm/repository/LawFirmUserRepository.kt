package com.katsadourose.lawcasemanager.lawfirm.repository

import com.katsadourose.lawcasemanager.lawfirm.model.LawFirmUser
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface LawFirmUserRepository : JpaRepository<LawFirmUser, UUID> {
    fun findByLawFirmId(lawFirmId: UUID): List<LawFirmUser>
}
