package com.katsadourose.lawcasemanager.platformuser.model

import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "platform_users")
data class PlatformUser(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,

    @Column(name = "law_firm_id", nullable = true)
    val lawFirmId: UUID? = null,

    @Column(nullable = false)
    val firstName: String,

    @Column(nullable = false)
    val lastName: String,

    @Column(nullable = false, unique = true)
    val email: String,

    @Column(nullable = false)
    val passwordHash: String,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val role: PlatformUserRole,

    @Column(nullable = false)
    val active: Boolean = true,

    val lastLoginAt: LocalDateTime? = null,

    @Column(nullable = false, updatable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(nullable = false)
    val updatedAt: LocalDateTime = LocalDateTime.now(),

    val barNumber: String? = null,

    val barAssociation: String? = null,

    val specialization: String? = null
)

enum class PlatformUserRole {
    SYSTEM_ADMIN,
    ADMIN_LAWYER,
    LAWYER,
    SECRETARY
}
