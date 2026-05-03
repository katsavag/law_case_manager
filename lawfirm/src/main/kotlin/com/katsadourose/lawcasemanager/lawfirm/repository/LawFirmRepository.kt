package com.katsadourose.lawcasemanager.lawfirm.repository

import com.katsadourose.lawcasemanager.lawfirm.model.LawFirm
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface LawFirmRepository : JpaRepository<LawFirm, UUID> {
    fun findByTaxNumber(taxNumber: String): LawFirm?
    fun findByEmail(email: String): LawFirm?
    fun findAllByActive(active: Boolean): List<LawFirm>
}
