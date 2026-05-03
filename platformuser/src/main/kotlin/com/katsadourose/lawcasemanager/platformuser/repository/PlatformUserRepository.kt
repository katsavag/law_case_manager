package com.katsadourose.lawcasemanager.platformuser.repository

import com.katsadourose.lawcasemanager.platformuser.model.PlatformUser
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface PlatformUserRepository : JpaRepository<PlatformUser, UUID> {
    fun findByLawFirmId(lawFirmId: UUID): List<PlatformUser>
    fun findByEmail(email: String): PlatformUser?
}
