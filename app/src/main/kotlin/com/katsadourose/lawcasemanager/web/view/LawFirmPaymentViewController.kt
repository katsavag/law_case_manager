package com.katsadourose.lawcasemanager.web.view

import com.katsadourose.lawcasemanager.lawfirm.dto.RecordPaymentRequest
import com.katsadourose.lawcasemanager.lawfirm.service.LawFirmPaymentService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID

@Controller
@RequestMapping("/admin/law-firms/{lawFirmId}/payments")
class LawFirmPaymentViewController(
    private val lawFirmPaymentService: LawFirmPaymentService
) {

    @GetMapping
    fun listPage(@PathVariable lawFirmId: UUID, model: Model): String {
        model.addAttribute("payments", lawFirmPaymentService.findAllByLawFirmId(lawFirmId))
        return "admin/law-firms/payments/list"
    }

    @GetMapping("/new")
    fun createPage(@PathVariable lawFirmId: UUID, model: Model): String {
        model.addAttribute("request", RecordPaymentRequest(
            lawFirmId = lawFirmId,
            amount = BigDecimal.ZERO,
            currency = "EUR",
            paidAt = LocalDateTime.now(),
            periodStart = LocalDateTime.now(),
            periodEnd = LocalDateTime.now().plusDays(30),
            notes = null,
            recordedBy = UUID.randomUUID() // Should ideally be current user, but no auth yet
        ))
        return "admin/law-firms/payments/form"
    }

    @PostMapping
    fun create(@PathVariable lawFirmId: UUID, @ModelAttribute request: RecordPaymentRequest): String {
        lawFirmPaymentService.recordPayment(request)
        return "redirect:/admin/law-firms/$lawFirmId/payments"
    }
}
