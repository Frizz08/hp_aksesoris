package com.example.hp_aksesoris.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.hp_aksesoris.model.Accessoris
import kotlinx.coroutines.flow.Flow

@Dao
interface AccessorisDao {
    @Query("SELECT * FROM accessoris_table ORDER BY name ASC")
    fun getAllAccessoris(): Flow<List<Accessoris>>

    @Insert
    suspend fun insertAccessoris(accessoris: Accessoris)

    @Delete
    suspend fun deleteAccessoris(accessoris: Accessoris)

    @Update fun updateAccessoris(accessoris: Accessoris)
}