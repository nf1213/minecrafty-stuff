package com.example.minecraftystuff

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.minecraftystuff.data.AppRepository
import com.example.minecraftystuff.data.Biome
import com.example.minecraftystuff.data.Location
import kotlinx.coroutines.launch

const val MINECRAFT_HORIZONTAL_WORLD_MAX = 29999872
const val MINECRAFT_VERTICAL_MIN = -64
const val MINECRAFT_VERTICAL_MAX = 320

class AddLocationViewModel(private val repository: AppRepository) : ViewModel() {

    var name by mutableStateOf("")
    var xValue by mutableStateOf(InputWrapper())
    var yValue by mutableStateOf(InputWrapper())
    var zValue by mutableStateOf(InputWrapper())
    var biome by mutableStateOf(Biome(-1, "", ""))

    val allBiomes: LiveData<List<Biome>> = repository.allBiomes.asLiveData()

    fun onNameChange(input: String) {
        name = input
    }

    fun onXChanged(input: String) {
        val intValue = input.trim().toIntOrNull() ?: 0
        xValue = InputWrapper(
            value = input,
            error = intValue !in -MINECRAFT_HORIZONTAL_WORLD_MAX..MINECRAFT_HORIZONTAL_WORLD_MAX
        )
    }

    fun onYChanged(input: String) {
        val intValue = input.trim().toIntOrNull() ?: 0
        yValue = InputWrapper(
            value = input,
            error = intValue !in MINECRAFT_VERTICAL_MIN..MINECRAFT_VERTICAL_MAX
        )
    }

    fun onZChanged(input: String) {
        val intValue = input.trim().toIntOrNull() ?: 0
        zValue = InputWrapper(
            value = input,
            error = intValue !in -MINECRAFT_HORIZONTAL_WORLD_MAX..MINECRAFT_HORIZONTAL_WORLD_MAX
        )
    }

    fun onBiomeChanged(biome: Biome) {
        this.biome = biome
    }

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun save(onSuccess: () -> Unit) = viewModelScope.launch {
        if (xValue.error || yValue.error || zValue.error) {
            return@launch
        }
        val location = Location(
            name = name,
            xCoordinate = xValue.value.trim().toInt(),
            yCoordinate = yValue.value.trim().toInt(),
            zCoordinate = zValue.value.trim().toInt(),
            biomeId = biome.id
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
