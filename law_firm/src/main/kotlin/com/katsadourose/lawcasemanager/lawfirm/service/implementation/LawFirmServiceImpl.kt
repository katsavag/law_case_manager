package com.katsadourose.lawcasemanager.lawfirm.service.implementation

import com.katsadourose.lawcasemanager.lawfirm.dto.OnboardLawFirmRequest
import com.katsadourose.lawcasemanager.lawfirm.dto.LawFirmResponse
import com.katsadourose.lawcasemanager.lawfirm.exception.LawFirmNotFoundException
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
        logger.info("Onboarding new law firm: ${request.name}")
        val lawFirm = request.toEntity()
        val savedLawFirm = lawFirmRepository.save(lawFirm)
        lawFirmSubscriptionService.createInitialSubscription(savedLawFirm.id!!)
        logger.info("Law firm onboarded successfully with id: ${savedLawFirm.id}")
        return savedLawFirm.toResponse()
    }

    override fun suspend(lawFirmId: UUID) {
        logger.info("Suspending law firm with id: $lawFirmId")
        val lawFirm = lawFirmRepository.findById(lawFirmId)
            .orElseThrow { LawFirmNotFoundException(lawFirmId) }
        lawFirm.active = false
        lawFirmRepository.save(lawFirm)
        logger.info("Law firm suspended successfully with id: $lawFirmId")
    }

    override fun activate(lawFirmId: UUID) {
        logger.info("Activating law firm with id: $lawFirmId")
        val lawFirm = lawFirmRepository.findById(lawFirmId)
            .orElseThrow { LawFirmNotFoundException(lawFirmId) }
        lawFirm.active = true
        lawFirmRepository.save(lawFirm)
        logger.info("Law firm activated successfully with id: $lawFirmId")
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
