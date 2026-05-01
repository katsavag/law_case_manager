package com.katsadourose.lawcasemanager.lawfirm.mapper

import com.katsadourose.lawcasemanager.lawfirm.model.LawFirmPayment
import com.katsadourose.lawcasemanager.lawfirm.dto.RecordPaymentRequest
import com.katsadourose.lawcasemanager.lawfirm.dto.LawFirmPaymentResponse

object LawFirmPaymentMapper {
    fun RecordPaymentRequest.toEntity(): LawFirmPayment {
        return LawFirmPayment(
            lawFirmId = this.lawFirmId,
            amount = this.amount,
            currency = this.currency,
            paidAt = this.paidAt,
            periodStart = this.periodStart,
            periodEnd = this.periodEnd,
            notes = this.notes,
            recordedBy = this.recordedBy
        )
    }

    fun LawFirmPayment.toResponse(): LawFirmPaymentResponse {
        return LawFirmPaymentResponse(
            id = this.id,
            lawFirmId = this.lawFirmId,
            amount = this.amount,
            currency = this.currency,
            paidAt = this.paidAt,
            periodStart = this.periodStart,
            periodEnd = this.periodEnd,
            notes = this.notes,
            recordedBy = this.recordedBy,
            createdAt = this.createdAt
        )
    }
}
