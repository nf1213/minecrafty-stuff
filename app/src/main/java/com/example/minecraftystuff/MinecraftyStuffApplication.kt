package com.example.minecraftystuff

import android.app.Application
import com.example.minecraftystuff.data.AppDatabase
import com.example.minecraftystuff.data.AppRepository

class MinecraftyStuffApplication : Application() {
    companion object {
        lateinit var instance: MinecraftyStuffApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy { AppDatabase.getDatabase(this) }
    val repository by lazy { AppRepository(database.locationDao()) }
}
