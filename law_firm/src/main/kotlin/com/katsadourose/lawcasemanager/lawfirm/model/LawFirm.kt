package com.katsadourose.lawcasemanager.lawfirm.model

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "law_firms")
data class LawFirm(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,

    val name: String,

    @Column(name = "tax_number", unique = true)
    val taxNumber: String,

    val address: String,

    val email: String,

    val phone: String,

    @Column(nullable = false)
    var active: Boolean = true,

    @OneToOne(mappedBy = "lawFirm", fetch = FetchType.EAGER)
    val subscription: LawFirmSubscription? = null,

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    val createdAt: LocalDateTime? = null,

    @UpdateTimestamp
    @Column(name = "updated_at")
    val updatedAt: LocalDateTime? = null
)