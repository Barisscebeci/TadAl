package com.barisscebeci.tadal.presentation.ui.screen.auth

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

object UserSession {
    var username by mutableStateOf<String?>(null)
}