package com.example.minecraftystuff

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.minecraftystuff.data.AppRepository
import com.example.minecraftystuff.data.Location
import kotlinx.coroutines.launch

const val MINECRAFT_HORIZONTAL_WORLD_MAX = 29999872
const val MINECRAFT_VERTICAL_MIN = -64
const val MINECRAFT_VERTICAL_MAX = 320

class AddLocationViewModel(private val repository: AppRepository) : ViewModel() {

    var state by mutableStateOf(AddLocationState())

    fun onNameChange(input: String) {
        state = state.copy(name = input)
    }

    fun onXChanged(input: String) {
        val intValue = input.trim().toIntOrNull() ?: 0
        state = state.copy(xCoordinate = InputWrapper(
            value = intValue.toString(),
            error = intValue !in -MINECRAFT_HORIZONTAL_WORLD_MAX..MINECRAFT_HORIZONTAL_WORLD_MAX
        ))
    }

    fun onYChanged(input: String) {
        val intValue = input.trim().toIntOrNull() ?: 0
        state = state.copy(yCoordinate = InputWrapper(
            value = intValue.toString(),
            error = intValue !in MINECRAFT_VERTICAL_MIN..MINECRAFT_VERTICAL_MAX
        ))
    }

    fun onZChanged(input: String) {
        val intValue = input.trim().toIntOrNull() ?: 0
        state = state.copy(zCoordinate = InputWrapper(
            value = intValue.toString(),
            error = intValue !in -MINECRAFT_HORIZONTAL_WORLD_MAX..MINECRAFT_HORIZONTAL_WORLD_MAX
        ))
    }

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun save(onSuccess: () -> Unit) = viewModelScope.launch {
        if (state.xCoordinate.error || state.yCoordinate.error || state.zCoordinate.error) {
            return@launch
        }
        val location = Location(
            name = state.name,
            xCoordinate = state.xCoordinate.value.toInt(),
            yCoordinate = state.yCoordinate.value.toInt(),
            zCoordinate = state.zCoordinate.value.toInt()
        )
        repository.insert(location)
        onSuccess()
    }
}

class AddLocationViewModelFactory(private val repository: AppRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddLocationViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AddLocationViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
