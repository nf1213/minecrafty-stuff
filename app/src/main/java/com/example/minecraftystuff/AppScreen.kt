package com.example.minecraftystuff

import androidx.annotation.StringRes

enum class AppScreen(
    @StringRes
    val itemName: Int
) {
    MainMenu(
        itemName = R.string.home
    ),
    Locations(
        itemName = R.string.locations
    ),
    AddLocation(
        itemName = R.string.add_location
    )
}
