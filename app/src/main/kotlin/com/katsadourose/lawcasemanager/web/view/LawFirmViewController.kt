package com.katsadourose.lawcasemanager.web.view

import com.katsadourose.lawcasemanager.lawfirm.dto.OnboardLawFirmRequest
import com.katsadourose.lawcasemanager.lawfirm.service.LawFirmService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import java.time.LocalDate
import java.util.UUID

@Controller
@RequestMapping("/admin/law-firms")
class LawFirmViewController(
    private val lawFirmService: LawFirmService
) {

    @GetMapping
    fun listPage(model: Model): String {
        model.addAttribute("lawFirms", lawFirmService.findAll())
        return "law-firms/list"
    }

    @GetMapping("/new")
    fun createPage(model: Model): String {
        model.addAttribute("lawFirm", OnboardLawFirmRequest(
            name = "",
            displayName = "",
            taxNumber = "",
            taxOffice = "",
            address = "",
            city = "",
            country = "",
            postalCode = "",
            email = "",
            phone = "",
            notes = null,
            plan = "Basic",
            subscriptionStartsAt = LocalDate.now(),
            billingPeriod = "MONTHLY",
            status = "ACTIVE",
            sendWelcomeEmail = true
        ))
        return "law-firms/create"
    }

    @PostMapping
    fun create(@ModelAttribute request: OnboardLawFirmRequest): String {
        lawFirmService.onboard(request)
        return "redirect:/admin/law-firms"
    }

    @GetMapping("/{lawFirmId}")
    fun detailPage(@PathVariable lawFirmId: UUID, model: Model): String {
        model.addAttribute("lawFirm", lawFirmService.findById(lawFirmId))
        return "law-firms/view"
    }

    @PostMapping("/{lawFirmId}/suspend")
    fun suspend(@PathVariable lawFirmId: UUID): String {
        lawFirmService.suspend(lawFirmId)
        return "redirect:/admin/law-firms/$lawFirmId"
    }

    @PostMapping("/{lawFirmId}/activate")
    fun activate(@PathVariable lawFirmId: UUID): String {
        lawFirmService.activate(lawFirmId)
        return "redirect:/admin/law-firms/$lawFirmId"
    }
}
