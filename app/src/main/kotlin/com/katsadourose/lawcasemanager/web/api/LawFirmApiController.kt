package com.katsadourose.lawcasemanager.web.api

import com.katsadourose.lawcasemanager.lawfirm.dto.OnboardLawFirmRequest
import com.katsadourose.lawcasemanager.lawfirm.dto.LawFirmResponse
import com.katsadourose.lawcasemanager.lawfirm.service.LawFirmService
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@RequestMapping("/api/admin/law-firms")
class LawFirmApiController(
    private val lawFirmService: LawFirmService
) {

    private val logger = LoggerFactory.getLogger(LawFirmApiController::class.java)

    @PostMapping
    fun onboard(@RequestBody request: OnboardLawFirmRequest): ResponseEntity<LawFirmResponse> {
        logger.info("Received request to onboard law firm: ${request.name}")
        val lawFirm = lawFirmService.onboard(request)
        return ResponseEntity.status(HttpStatus.CREATED).body(lawFirm)
    }

    @GetMapping
    fun findAll(): ResponseEntity<List<LawFirmResponse>> {
        logger.info("Received request to find all law firms")
        val lawFirms = lawFirmService.findAll()
        return ResponseEntity.ok(lawFirms)
    }

    @GetMapping("/{lawFirmId}")
    fun findById(@PathVariable lawFirmId: UUID): ResponseEntity<LawFirmResponse> {
        logger.info("Received request to find law firm with id: $lawFirmId")
        val lawFirm = lawFirmService.findById(lawFirmId)
        return ResponseEntity.ok(lawFirm)
    }

    @PatchMapping("/{lawFirmId}/suspend")
    fun suspend(@PathVariable lawFirmId: UUID): ResponseEntity<Void> {
        logger.info("Received request to suspend law firm with id: $lawFirmId")
        lawFirmService.suspend(lawFirmId)
        return ResponseEntity.noContent().build()
    }

    @PatchMapping("/{lawFirmId}/activate")
    fun activate(@PathVariable lawFirmId: UUID): ResponseEntity<Void> {
        logger.info("Received request to activate law firm with id: $lawFirmId")
        lawFirmService.activate(lawFirmId)
        return ResponseEntity.noContent().build()
    }
}
