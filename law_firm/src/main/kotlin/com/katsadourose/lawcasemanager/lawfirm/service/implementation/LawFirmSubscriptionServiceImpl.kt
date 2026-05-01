package com.katsadourose.lawcasemanager.lawfirm.service.implementation

import com.katsadourose.lawcasemanager.lawfirm.exception.LawFirmNotFoundException
import com.katsadourose.lawcasemanager.lawfirm.model.LawFirmSubscription
import com.katsadourose.lawcasemanager.lawfirm.model.SubscriptionStatus
import com.katsadourose.lawcasemanager.lawfirm.repository.LawFirmSubscriptionRepository
import com.katsadourose.lawcasemanager.lawfirm.service.LawFirmSubscriptionService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import java.util.UUID

@Service
@Transactional
class LawFirmSubscriptionServiceImpl(
    private val lawFirmSubscriptionRepository: LawFirmSubscriptionRepository
) : LawFirmSubscriptionService {

    private val logger = LoggerFactory.getLogger(LawFirmSubscriptionServiceImpl::class.java)

    override fun createInitialSubscription(lawFirmId: UUID): LawFirmSubscription {
        logger.info("Creating initial subscription for law firm with id: $lawFirmId")
        val now = LocalDateTime.now()
        val subscription = LawFirmSubscription(
            lawFirmId = lawFirmId,
            status = SubscriptionStatus.ACTIVE,
            currentPeriodStart = now,
            currentPeriodEnd = now.plusDays(30),
            updatedAt = now
        )
        val savedSubscription = lawFirmSubscriptionRepository.save(subscription)
        logger.info("Initial subscription created successfully with id: ${savedSubscription.id}")
        return savedSubscription
    }

    override fun renewSubscription(lawFirmId: UUID, periodEnd: LocalDateTime): LawFirmSubscription {
        logger.info("Renewing subscription for law firm with id: $lawFirmId")
        val subscription = findByLawFirmId(lawFirmId)
        val updatedSubscription = subscription.copy(
            currentPeriodEnd = periodEnd,
            status = SubscriptionStatus.ACTIVE,
            updatedAt = LocalDateTime.now()
        )
        val savedSubscription = lawFirmSubscriptionRepository.save(updatedSubscription)
        logger.info("Subscription renewed successfully for law firm with id: $lawFirmId")
        return savedSubscription
    }

    override fun expireSubscription(lawFirmId: UUID): LawFirmSubscription {
        logger.info("Expiring subscription for law firm with id: $lawFirmId")
        val subscription = findByLawFirmId(lawFirmId)
        val updatedSubscription = subscription.copy(
            status = SubscriptionStatus.EXPIRED,
            updatedAt = LocalDateTime.now()
        )
        val savedSubscription = lawFirmSubscriptionRepository.save(updatedSubscription)
        logger.info("Subscription expired successfully for law firm with id: $lawFirmId")
        return savedSubscription
    }

    override fun findByLawFirmId(lawFirmId: UUID): LawFirmSubscription {
        return lawFirmSubscriptionRepository.findByLawFirmId(lawFirmId)
            ?: throw LawFirmNotFoundException(lawFirmId)
    }
}
