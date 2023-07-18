package com.example.hp_aksesoris.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "accessoris_table")
data class Accessoris(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val address: String,
    val latitude: Double?,
    val longitude: Double?

    //menambah data latitude dan longitude untuk disimpan dalam table
) : Parcelable