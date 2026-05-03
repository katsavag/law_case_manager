package com.katsadourose.lawcasemanager.lawfirm.service.implementation

import com.katsadourose.lawcasemanager.lawfirm.dto.OnboardLawFirmRequest
import com.katsadourose.lawcasemanager.lawfirm.dto.LawFirmResponse
import com.katsadourose.lawcasemanager.lawfirm.exception.LawFirmNotFoundException
import com.katsadourose.lawcasemanager.lawfirm.logging.EventCode
import com.katsadourose.lawcasemanager.lawfirm.mapper.LawFirmMapper.toEntity
import com.katsadourose.lawcasemanager.lawfirm.mapper.LawFirmMapper.toResponse
import com.katsadourose.lawcasemanager.lawfirm.repository.LawFirmRepository
import com.katsadourose.lawcasemanager.lawfirm.service.LawFirmService
import com.katsadourose.lawcasemanager.lawfirm.service.LawFirmSubscriptionService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
@Transactional
class LawFirmServiceImpl(
    private val lawFirmRepository: LawFirmRepository,
    private val lawFirmSubscriptionService: LawFirmSubscriptionService
) : LawFirmService {

    private val logger = LoggerFactory.getLogger(LawFirmServiceImpl::class.java)

    override fun onboard(request: OnboardLawFirmRequest): LawFirmResponse {
        logger.atInfo()
            .addKeyValue("eventCode", EventCode.LAW_FIRM_ONBOARDING_STARTED.code)
            .addKeyValue("lawFirmName", request.name)
            .log("Onboarding new law firm")
        val lawFirm = request.toEntity()
        val savedLawFirm = lawFirmRepository.save(lawFirm)
        lawFirmSubscriptionService.createInitialSubscription(savedLawFirm)
        logger.atInfo()
            .addKeyValue("eventCode", EventCode.LAW_FIRM_ONBOARDED.code)
            .addKeyValue("lawFirmId", savedLawFirm.id)
            .addKeyValue("lawFirmName", savedLawFirm.name)
            .log("Law firm onboarded successfully")
        return savedLawFirm.toResponse()
    }

    override fun suspend(lawFirmId: UUID) {
        logger.atInfo()
            .addKeyValue("eventCode", EventCode.LAW_FIRM_SUSPENSION_STARTED.code)
            .addKeyValue("lawFirmId", lawFirmId)
            .log("Suspending law firm")
        val lawFirm = lawFirmRepository.findById(lawFirmId)
            .orElseThrow { LawFirmNotFoundException(lawFirmId) }
        lawFirm.active = false
        lawFirmRepository.save(lawFirm)
        logger.atInfo()
            .addKeyValue("eventCode", EventCode.LAW_FIRM_SUSPENDED.code)
            .addKeyValue("lawFirmId", lawFirmId)
            .log("Law firm suspended successfully")
    }

    override fun activate(lawFirmId: UUID) {
        logger.atInfo()
            .addKeyValue("eventCode", EventCode.LAW_FIRM_ACTIVATION_STARTED.code)
            .addKeyValue("lawFirmId", lawFirmId)
            .log("Activating law firm")
        val lawFirm = lawFirmRepository.findById(lawFirmId)
            .orElseThrow { LawFirmNotFoundException(lawFirmId) }
        lawFirm.active = true
        lawFirmRepository.save(lawFirm)
        logger.atInfo()
            .addKeyValue("eventCode", EventCode.LAW_FIRM_ACTIVATED.code)
            .addKeyValue("lawFirmId", lawFirmId)
            .log("Law firm activated successfully")
    }

    override fun findById(lawFirmId: UUID): LawFirmResponse {
        return lawFirmRepository.findById(lawFirmId)
            .orElseThrow { LawFirmNotFoundException(lawFirmId) }
            .toResponse()
    }

    override fun findAll(): List<LawFirmResponse> {
        return lawFirmRepository.findAll().map { it.toResponse() }
    }
}
