package com.example.minecraftystuff.data

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Entity(tableName = "location_table")
class Location(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "name") var name: String = "",
    @ColumnInfo(name = "x_coordinate") var xCoordinate: Int = 0,
    @ColumnInfo(name = "y_coordinate") var yCoordinate: Int = 0,
    @ColumnInfo(name = "z_coordinate") var zCoordinate: Int = 0
)

@Dao
interface LocationDao {
    @Query("SELECT * FROM location_table ORDER BY id ASC")
    fun getAllLocations(): Flow<List<Location>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(location: Location)
}
