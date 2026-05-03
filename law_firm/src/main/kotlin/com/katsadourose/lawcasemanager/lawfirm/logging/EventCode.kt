package com.katsadourose.lawcasemanager.lawfirm.logging

enum class EventCode(val code: String) {
    // Law firm onboarding events
    LAW_FIRM_ONBOARDING_STARTED("FRM_001"),
    LAW_FIRM_ONBOARDED("FRM_002"),
    
    // Law firm activation/suspension events
    LAW_FIRM_SUSPENSION_STARTED("FRM_003"),
    LAW_FIRM_SUSPENDED("FRM_004"),
    LAW_FIRM_ACTIVATION_STARTED("FRM_005"),
    LAW_FIRM_ACTIVATED("FRM_006"),
    
    // Law firm subscription events
    SUBSCRIPTION_CREATION_STARTED("FRM_007"),
    SUBSCRIPTION_CREATED("FRM_008"),
    SUBSCRIPTION_RENEWAL_STARTED("FRM_009"),
    SUBSCRIPTION_RENEWED("FRM_010"),
    SUBSCRIPTION_EXPIRATION_STARTED("FRM_011"),
    SUBSCRIPTION_EXPIRED("FRM_012"),
    
    // Law firm payment events
    PAYMENT_RECORDING_STARTED("FRM_013"),
    PAYMENT_RECORDED("FRM_014"),
    
    // Error events
    LAW_FIRM_NOT_FOUND("FRM_099")
}
