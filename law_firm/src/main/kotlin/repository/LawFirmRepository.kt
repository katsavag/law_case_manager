package repository

import model.LawFirm
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface LawFirmRepository : JpaRepository<LawFirm, UUID> {
    fun findByTaxNumber(taxNumber: String): LawFirm?
    fun existsByTaxNumber(taxNumber: String): Boolean
}