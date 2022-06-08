package com.example.minecraftystuff

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.minecraftystuff.data.AppRepository
import com.example.minecraftystuff.data.Location
import kotlinx.coroutines.launch

class LocationsViewModel(private val repository: AppRepository) : ViewModel() {

    // Using LiveData and caching what allLocations returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allLocations: LiveData<List<Location>> = repository.allLocations.asLiveData()

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(location: Location) = viewModelScope.launch {
        repository.insert(location)
    }
}


class LocationsViewModelFactory(private val repository: AppRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LocationsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LocationsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
