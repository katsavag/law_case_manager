package com.katsadourose.lawcasemanager.lawfirm.service.implementation

import com.katsadourose.lawcasemanager.lawfirm.dto.CreateLawFirmUserRequest
import com.katsadourose.lawcasemanager.lawfirm.exception.LawFirmUserNotFoundException
import com.katsadourose.lawcasemanager.lawfirm.mapper.LawFirmUserMapper.toEntity
import com.katsadourose.lawcasemanager.lawfirm.model.LawFirmUser
import com.katsadourose.lawcasemanager.lawfirm.repository.LawFirmRepository
import com.katsadourose.lawcasemanager.lawfirm.repository.LawFirmUserRepository
import com.katsadourose.lawcasemanager.lawfirm.service.LawFirmUserService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import java.util.*

@Service
@Transactional
class LawFirmUserServiceImpl(
    private val lawFirmUserRepository: LawFirmUserRepository,
    private val lawFirmRepository: LawFirmRepository
) : LawFirmUserService {
    private val logger = LoggerFactory.getLogger(javaClass)

    override fun createUser(request: CreateLawFirmUserRequest): LawFirmUser {
        val lawFirm = lawFirmRepository.findById(request.lawFirmId).orElseThrow {
            logger.error("Law firm not found [lawFirmId={}]", request.lawFirmId)
            throw IllegalArgumentException("Law firm not found")
        }

        val user = request.toEntity(lawFirm)
        val savedUser = lawFirmUserRepository.save(user)

        logger.info(
            "Law firm user created [userId={}, lawFirmId={}, email={}, role={}]",
            savedUser.id,
            request.lawFirmId,
            request.email,
            request.role
        )

        return savedUser
    }

    @Transactional(readOnly = true)
    override fun findById(userId: UUID): LawFirmUser {
        return lawFirmUserRepository.findById(userId).orElseThrow {
            logger.error("Law firm user not found [userId={}]", userId)
            LawFirmUserNotFoundException(userId)
        }
    }

    @Transactional(readOnly = true)
    override fun findUsersByLawFirm(lawFirmId: UUID): List<LawFirmUser> {
        return lawFirmUserRepository.findByLawFirmId(lawFirmId)
    }

    override fun updateBasicInfo(userId: UUID, firstName: String, lastName: String): LawFirmUser {
        val user = findById(userId)

        val updatedUser = user.copy(
            firstName = firstName,
            lastName = lastName,
            updatedAt = LocalDateTime.now()
        )

        val savedUser = lawFirmUserRepository.save(updatedUser)

        logger.info(
            "Law firm user basic info updated [userId={}, firstName={}, lastName={}]",
            userId,
            firstName,
            lastName
        )

        return savedUser
    }
}
