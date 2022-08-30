package com.oolong.screentimer20.domain.model

data class AppUtilityData(
    var numberOfRunning: Int = 0,
    var isCountdownTimerRunning: Boolean = false,
    var isAppRegisteredAsDeviceAdmin: Boolean = false
)
