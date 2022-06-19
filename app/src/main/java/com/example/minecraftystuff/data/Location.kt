package com.example.minecraftystuff.data

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Relation
import kotlinx.coroutines.flow.Flow

@Entity(tableName = "location_table")
class Location(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "name") var name: String = "",
    @ColumnInfo(name = "x_coordinate") var xCoordinate: Int = 0,
    @ColumnInfo(name = "y_coordinate") var yCoordinate: Int = 0,
    @ColumnInfo(name = "z_coordinate") var zCoordinate: Int = 0,
    @ColumnInfo(name = "biome_id") var biomeId: Int? = null
)

data class LocationWithBiome(
    @Embedded val location: Location,
    @Relation(
        parentColumn = "biome_id",
        entityColumn = "id"
    )
    val biome: Biome? = null
)

@Dao
interface LocationDao {
    @Query("SELECT * FROM location_table ORDER BY id ASC")
    fun getAllLocations(): Flow<List<LocationWithBiome>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(location: Location)

    @Delete
    suspend fun delete(location: Location)
}
