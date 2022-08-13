package com.oolong.screentimer20.presentation.duration_entry_screen

import com.oolong.screentimer20.domain.Keypad

sealed class DurationEntryScreenEvent {
    data class OnKeypadPressed(val value: Keypad): DurationEntryScreenEvent()
}
