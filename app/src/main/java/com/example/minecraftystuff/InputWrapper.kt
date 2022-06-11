package com.example.minecraftystuff

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


// https://github.com/Skyyo/compose-inputs-validation
@Parcelize
data class InputWrapper(val value: String = "", val error: Boolean = false) : Parcelable
