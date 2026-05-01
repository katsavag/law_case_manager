package com.katsadourose.lawcasemanager.lawfirm.exception

import java.util.UUID

class LawFirmNotFoundException(lawFirmId: UUID) : RuntimeException("Law firm not found [lawFirmId=$lawFirmId]")
