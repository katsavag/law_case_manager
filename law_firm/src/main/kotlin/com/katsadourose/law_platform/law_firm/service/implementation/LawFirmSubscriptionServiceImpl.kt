package com.katsadourose.law_platform.law_firm.service.implementation

import com.katsadourose.law_platform.law_firm.model.LawFirmSubscription
import com.katsadourose.law_platform.law_firm.model.SubscriptionStatus
import com.katsadourose.law_platform.law_firm.repository.LawFirmSubscriptionRepository
import com.katsadourose.law_platform.law_firm.service.LawFirmSubscriptionService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import java.util.UUID

@Service
@Transactional
class LawFirmSubscriptionServiceImpl(
    private val lawFirmSubscriptionRepository: LawFirmSubscriptionRepository
) : LawFirmSubscriptionService {

    override fun createInitialSubscription(lawFirmId: UUID): LawFirmSubscription {
        val now = LocalDateTime.now()
        val subscription = LawFirmSubscription(
            lawFirmId = lawFirmId,
            status = SubscriptionStatus.ACTIVE,
            currentPeriodStart = now,
            currentPeriodEnd = now.plusDays(30),
            updatedAt = now
        )
        return lawFirmSubscriptionRepository.save(subscription)
    }

    override fun renewSubscription(lawFirmId: UUID, periodEnd: LocalDateTime): LawFirmSubscription {
        val subscription = findByLawFirmId(lawFirmId)
        val updatedSubscription = subscription.copy(
            currentPeriodEnd = periodEnd,
            status = SubscriptionStatus.ACTIVE,
            updatedAt = LocalDateTime.now()
        )
        return lawFirmSubscriptionRepository.save(updatedSubscription)
    }

    override fun expireSubscription(lawFirmId: UUID): LawFirmSubscription {
        val subscription = findByLawFirmId(lawFirmId)
        val updatedSubscription = subscription.copy(
            status = SubscriptionStatus.EXPIRED,
            updatedAt = LocalDateTime.now()
        )
        return lawFirmSubscriptionRepository.save(updatedSubscription)
    }

    override fun findByLawFirmId(lawFirmId: UUID): LawFirmSubscription {
        return lawFirmSubscriptionRepository.findByLawFirmId(lawFirmId)
            ?: TODO("NotFoundException")
    }
}
