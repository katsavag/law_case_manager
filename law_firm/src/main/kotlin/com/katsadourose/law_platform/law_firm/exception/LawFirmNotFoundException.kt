package com.katsadourose.law_platform.law_firm.exception

import java.util.UUID

class LawFirmNotFoundException(lawFirmId: UUID) : RuntimeException("Law firm not found [lawFirmId=$lawFirmId]")
