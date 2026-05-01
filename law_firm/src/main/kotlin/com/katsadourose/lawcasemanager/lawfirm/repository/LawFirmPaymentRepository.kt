package com.katsadourose.lawcasemanager.lawfirm.repository

import com.katsadourose.lawcasemanager.lawfirm.model.LawFirmPayment
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface LawFirmPaymentRepository : JpaRepository<LawFirmPayment, UUID> {
    fun findAllByLawFirmId(lawFirmId: UUID): List<LawFirmPayment>
    fun findAllByLawFirmIdOrderByPaidAtDesc(lawFirmId: UUID): List<LawFirmPayment>
}
