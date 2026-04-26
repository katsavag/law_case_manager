package com.katsadourose.law_platform.law_firm.service.implementation

import com.katsadourose.law_platform.law_firm.model.LawFirmPayment
import com.katsadourose.law_platform.law_firm.dto.RecordPaymentRequest
import com.katsadourose.law_platform.law_firm.mapper.LawFirmPaymentMapper.toEntity
import com.katsadourose.law_platform.law_firm.repository.LawFirmPaymentRepository
import com.katsadourose.law_platform.law_firm.service.LawFirmPaymentService
import com.katsadourose.law_platform.law_firm.service.LawFirmSubscriptionService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
@Transactional
class LawFirmPaymentServiceImpl(
    private val lawFirmPaymentRepository: LawFirmPaymentRepository,
    private val lawFirmSubscriptionService: LawFirmSubscriptionService
) : LawFirmPaymentService {

    override fun recordPayment(request: RecordPaymentRequest): LawFirmPayment {
        val payment = request.toEntity()
        val savedPayment = lawFirmPaymentRepository.save(payment)
        lawFirmSubscriptionService.renewSubscription(request.lawFirmId, request.periodEnd)
        return savedPayment
    }

    override fun findAllByLawFirmId(lawFirmId: UUID): List<LawFirmPayment> {
        return lawFirmPaymentRepository.findAllByLawFirmId(lawFirmId)
    }
}
