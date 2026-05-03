package com.katsadourose.lawcasemanager.lawfirm.exception

import com.katsadourose.lawcasemanager.common.exception.AppException
import java.util.UUID

class LawFirmNotFoundException(lawFirmId: UUID) : AppException(
    errorCode = ErrorCode.LAW_FIRM_NOT_FOUND.name,
    message = "Law firm not found [lawFirmId=$lawFirmId]"
)
