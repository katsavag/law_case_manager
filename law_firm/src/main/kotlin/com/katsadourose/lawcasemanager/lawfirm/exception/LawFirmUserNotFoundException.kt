package com.katsadourose.lawcasemanager.lawfirm.exception

import java.util.*

class LawFirmUserNotFoundException(userId: UUID) : RuntimeException("Law firm user not found [userId=$userId]")
