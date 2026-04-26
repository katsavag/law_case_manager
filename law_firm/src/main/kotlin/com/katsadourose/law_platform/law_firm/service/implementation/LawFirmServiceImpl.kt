package com.katsadourose.law_platform.law_firm.service.implementation

import com.katsadourose.law_platform.law_firm.model.LawFirm
import com.katsadourose.law_platform.law_firm.dto.OnboardLawFirmRequest
import com.katsadourose.law_platform.law_firm.exception.LawFirmNotFoundException
import com.katsadourose.law_platform.law_firm.mapper.LawFirmMapper.toEntity
import com.katsadourose.law_platform.law_firm.repository.LawFirmRepository
import com.katsadourose.law_platform.law_firm.service.LawFirmService
import com.katsadourose.law_platform.law_firm.service.LawFirmSubscriptionService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
@Transactional
class LawFirmServiceImpl(
    private val lawFirmRepository: LawFirmRepository,
    private val lawFirmSubscriptionService: LawFirmSubscriptionService
) : LawFirmService {

    override fun onboard(request: OnboardLawFirmRequest): LawFirm {
        val lawFirm = request.toEntity()
        val savedLawFirm = lawFirmRepository.save(lawFirm)
        lawFirmSubscriptionService.createInitialSubscription(savedLawFirm.id!!)
        return savedLawFirm
    }

    override fun suspend(lawFirmId: UUID) {
        val lawFirm = findById(lawFirmId)
        lawFirm.active = false
        lawFirmRepository.save(lawFirm)
    }

    override fun activate(lawFirmId: UUID) {
        val lawFirm = findById(lawFirmId)
        lawFirm.active = true
        lawFirmRepository.save(lawFirm)
    }

    override fun findById(lawFirmId: UUID): LawFirm {
        return lawFirmRepository.findById(lawFirmId)
            .orElseThrow { LawFirmNotFoundException(lawFirmId) }
    }

    override fun findAll(): List<LawFirm> {
        return lawFirmRepository.findAll()
    }
}
