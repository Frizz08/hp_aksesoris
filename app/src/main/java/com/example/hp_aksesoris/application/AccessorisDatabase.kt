package com.example.hp_aksesoris.application

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.hp_aksesoris.dao.AccessorisDao
import com.example.hp_aksesoris.model.Accessoris

@Database(entities = [Accessoris::class], version = 1, exportSchema = false)
abstract class AccessorisDatabase: RoomDatabase() {
    abstract fun accessorisDao(): AccessorisDao

    companion object{
        private var INSTANCE: AccessorisDatabase? = null

        //migrasi database versi 1 ke 2, karena ada perubahan table
        private val migration1to2: Migration= object : Migration(1, 2){
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE accessoris_table ADD COLUMN latitude Double DEFAULT 0.0")
                database.execSQL("ALTER TABLE accessoris_table ADD COLUMN longitude Double DEFAULT 0.0")
            }
        }
        fun getDatabase(context: Context): AccessorisDatabase {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AccessorisDatabase::class.java,
                    "accessoris_database"
                )
                    .addMigrations(migration1to2)
                    .allowMainThreadQueries()
                    .build()

                INSTANCE= instance
                instance
            }
        }
    }
}