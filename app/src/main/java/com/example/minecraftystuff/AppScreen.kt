package com.example.minecraftystuff

import androidx.annotation.StringRes

enum class AppScreen(
    @StringRes
    val label: Int
) {
    MainMenu(label = R.string.home),
    Locations(label = R.string.locations),
    AddLocation(label = R.string.add_location);

    companion object {
        fun from(name: String?): AppScreen? {
            return values().find {
                it.name == name
            }
        }
    }
}
