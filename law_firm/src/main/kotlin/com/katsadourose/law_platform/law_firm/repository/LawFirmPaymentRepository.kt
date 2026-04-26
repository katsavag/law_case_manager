package com.katsadourose.law_platform.law_firm.repository

import com.katsadourose.law_platform.law_firm.model.LawFirmPayment
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface LawFirmPaymentRepository : JpaRepository<LawFirmPayment, UUID> {
    fun findAllByLawFirmId(lawFirmId: UUID): List<LawFirmPayment>
    fun findAllByLawFirmIdOrderByPaidAtDesc(lawFirmId: UUID): List<LawFirmPayment>
}
