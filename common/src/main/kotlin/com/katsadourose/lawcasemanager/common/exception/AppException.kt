package com.katsadourose.lawcasemanager.common.exception

/**
 * Base exception for all application-specific exceptions.
 * All module exceptions must inherit from this class and define their own error codes.
 */
abstract class AppException(
    val errorCode: String,
    override val message: String,
    cause: Throwable? = null
) : RuntimeException(message, cause)
