package com.oolong.screentimer20.domain

sealed class Keypad(
    val value: String
) {
    object Key1: Keypad("1")
    object Key2: Keypad("2")
    object Key3: Keypad("3")
    object Key4: Keypad("4")
    object Key5: Keypad("5")
    object Key6: Keypad("6")
    object Key7: Keypad("7")
    object Key8: Keypad("8")
    object Key9: Keypad("9")
    object Key0: Keypad("0")
    object KeyDelete: Keypad("x")
    object KeyPlay: Keypad("play")
}
