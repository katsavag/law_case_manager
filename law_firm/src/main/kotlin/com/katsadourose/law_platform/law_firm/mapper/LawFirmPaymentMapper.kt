package com.katsadourose.law_platform.law_firm.mapper

import com.katsadourose.law_platform.law_firm.model.LawFirmPayment
import com.katsadourose.law_platform.law_firm.dto.RecordPaymentRequest

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
}
