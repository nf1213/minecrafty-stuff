package com.example.minecraftystuff.data

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Entity(tableName = "location_biome_table", indices = [Index(value = ["identifier"], unique = true)])
class Biome(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var name: String,
    val identifier: String
)

@Dao
interface BiomeDao {
    @Query("SELECT * FROM location_biome_table ORDER BY id ASC")
    fun getAllBiomes(): Flow<List<Biome>>
}
