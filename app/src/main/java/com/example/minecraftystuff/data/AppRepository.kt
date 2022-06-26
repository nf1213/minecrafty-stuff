package com.example.minecraftystuff.data

import androidx.annotation.WorkerThread
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVPrinter
import java.io.File

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class AppRepository(private val locationDao: LocationDao, biomeDao: BiomeDao) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allLocations: Flow<List<LocationWithBiome>> = locationDao.getAllLocations()

    val allBiomes: Flow<List<Biome>> = biomeDao.getAllBiomes()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(location: Location) {
        locationDao.insert(location)
    }

    @WorkerThread
    suspend fun delete(location: Location) {
        locationDao.delete(location)
    }

    suspend fun exportLocations(): File = withContext(Dispatchers.IO) {
        val file = File.createTempFile("locations_", ".csv")
        val fileWriter = file.bufferedWriter()
        val csvPrinter = CSVPrinter(fileWriter, CSVFormat.DEFAULT
            .withHeader("name", "x", "y", "z", "biomeId"))
        allLocations.first().forEach {
            csvPrinter.printRecord(
                it.location.name,
                it.location.xCoordinate,
                it.location.yCoordinate,
                it.location.zCoordinate,
                it.location.biomeId
            )
        }
        csvPrinter.flush()
        csvPrinter.close()
        return@withContext file
    }
}
