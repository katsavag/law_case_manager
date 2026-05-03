package com.katsadourose.lawcasemanager.lawfirm.service.implementation

import com.katsadourose.lawcasemanager.lawfirm.dto.RecordPaymentRequest
import com.katsadourose.lawcasemanager.lawfirm.dto.LawFirmPaymentResponse
import com.katsadourose.lawcasemanager.lawfirm.logging.EventCode
import com.katsadourose.lawcasemanager.lawfirm.mapper.LawFirmPaymentMapper.toEntity
import com.katsadourose.lawcasemanager.lawfirm.mapper.LawFirmPaymentMapper.toResponse
import com.katsadourose.lawcasemanager.lawfirm.repository.LawFirmPaymentRepository
import com.katsadourose.lawcasemanager.lawfirm.service.LawFirmPaymentService
import com.katsadourose.lawcasemanager.lawfirm.service.LawFirmSubscriptionService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
@Transactional
class LawFirmPaymentServiceImpl(
    private val lawFirmPaymentRepository: LawFirmPaymentRepository,
    private val lawFirmSubscriptionService: LawFirmSubscriptionService
) : LawFirmPaymentService {

    private val logger = LoggerFactory.getLogger(LawFirmPaymentServiceImpl::class.java)

    override fun recordPayment(request: RecordPaymentRequest): LawFirmPaymentResponse {
        logger.atInfo()
            .addKeyValue("eventCode", EventCode.PAYMENT_RECORDING_STARTED.code)
            .addKeyValue("lawFirmId", request.lawFirmId)
            .addKeyValue("amount", request.amount)
            .addKeyValue("periodEnd", request.periodEnd)
            .log("Recording payment for law firm")
        val payment = request.toEntity()
        val savedPayment = lawFirmPaymentRepository.save(payment)
        lawFirmSubscriptionService.renewSubscription(request.lawFirmId, request.periodEnd)
        logger.atInfo()
            .addKeyValue("eventCode", EventCode.PAYMENT_RECORDED.code)
            .addKeyValue("paymentId", savedPayment.id)
            .addKeyValue("lawFirmId", request.lawFirmId)
            .log("Payment recorded successfully")
        return savedPayment.toResponse()
    }

    override fun findAllByLawFirmId(lawFirmId: UUID): List<LawFirmPaymentResponse> {
        return lawFirmPaymentRepository.findAllByLawFirmId(lawFirmId).map { it.toResponse() }
    }
}
