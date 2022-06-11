package com.example.minecraftystuff

data class AddLocationState(
    var name: String = "",
    var xCoordinate: InputWrapper = InputWrapper(),
    var yCoordinate: InputWrapper = InputWrapper(),
    var zCoordinate: InputWrapper = InputWrapper()
)
