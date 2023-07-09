package com.example.hp_aksesoris.application

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.hp_aksesoris.dao.AccessorisDao
import com.example.hp_aksesoris.model.Accessoris

@Database(entities = [Accessoris::class], version = 1, exportSchema = false)
abstract class AccessorisDatabase: RoomDatabase() {
    abstract fun accessorisDao(): AccessorisDao

    companion object{
        private var INSTANCE: AccessorisDatabase? = null

        fun getDatabase(context: Context): AccessorisDatabase {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AccessorisDatabase::class.java,
                    "accessoris_database"
                )
                    .allowMainThreadQueries()
                    .build()

                INSTANCE= instance
                instance
            }
        }
    }
}