package com.katsadourose.lawcasemanager.lawfirm.service.implementation

import com.katsadourose.lawcasemanager.lawfirm.exception.LawFirmNotFoundException
import com.katsadourose.lawcasemanager.lawfirm.logging.EventCode
import com.katsadourose.lawcasemanager.lawfirm.model.LawFirm
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

    override fun createInitialSubscription(lawFirm: LawFirm): LawFirmSubscription {
        logger.atInfo()
            .addKeyValue("eventCode", EventCode.SUBSCRIPTION_CREATION_STARTED.code)
            .addKeyValue("lawFirmId", lawFirm.id)
            .log("Creating initial subscription for law firm")
        val now = LocalDateTime.now()
        val subscription = LawFirmSubscription(
            lawFirm = lawFirm,
            status = SubscriptionStatus.ACTIVE,
            currentPeriodStart = now,
            currentPeriodEnd = now.plusDays(30),
            updatedAt = now
        )
        val savedSubscription = lawFirmSubscriptionRepository.save(subscription)
        logger.atInfo()
            .addKeyValue("eventCode", EventCode.SUBSCRIPTION_CREATED.code)
            .addKeyValue("subscriptionId", savedSubscription.id)
            .addKeyValue("lawFirmId", lawFirm.id)
            .log("Initial subscription created successfully")
        return savedSubscription
    }

    override fun renewSubscription(lawFirmId: UUID, periodEnd: LocalDateTime): LawFirmSubscription {
        logger.atInfo()
            .addKeyValue("eventCode", EventCode.SUBSCRIPTION_RENEWAL_STARTED.code)
            .addKeyValue("lawFirmId", lawFirmId)
            .addKeyValue("periodEnd", periodEnd)
            .log("Renewing subscription for law firm")
        val subscription = findByLawFirmId(lawFirmId)
        val updatedSubscription = subscription.copy(
            currentPeriodEnd = periodEnd,
            status = SubscriptionStatus.ACTIVE,
            updatedAt = LocalDateTime.now()
        )
        val savedSubscription = lawFirmSubscriptionRepository.save(updatedSubscription)
        logger.atInfo()
            .addKeyValue("eventCode", EventCode.SUBSCRIPTION_RENEWED.code)
            .addKeyValue("lawFirmId", lawFirmId)
            .addKeyValue("subscriptionId", savedSubscription.id)
            .log("Subscription renewed successfully")
        return savedSubscription
    }

    override fun expireSubscription(lawFirmId: UUID): LawFirmSubscription {
        logger.atInfo()
            .addKeyValue("eventCode", EventCode.SUBSCRIPTION_EXPIRATION_STARTED.code)
            .addKeyValue("lawFirmId", lawFirmId)
            .log("Expiring subscription for law firm")
        val subscription = findByLawFirmId(lawFirmId)
        val updatedSubscription = subscription.copy(
            status = SubscriptionStatus.EXPIRED,
            updatedAt = LocalDateTime.now()
        )
        val savedSubscription = lawFirmSubscriptionRepository.save(updatedSubscription)
        logger.atInfo()
            .addKeyValue("eventCode", EventCode.SUBSCRIPTION_EXPIRED.code)
            .addKeyValue("lawFirmId", lawFirmId)
            .addKeyValue("subscriptionId", savedSubscription.id)
            .log("Subscription expired successfully")
        return savedSubscription
    }

    override fun findByLawFirmId(lawFirmId: UUID): LawFirmSubscription {
        return lawFirmSubscriptionRepository.findByLawFirm_Id(lawFirmId)
            ?: throw LawFirmNotFoundException(lawFirmId)
    }
}
