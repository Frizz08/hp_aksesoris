package com.example.hp_aksesoris.application

import android.app.Application
import com.example.hp_aksesoris.repository.AccessorisRepository

class AccessorisApp: Application() {
    val database by lazy { AccessorisDatabase.getDatabase(this) }
    val repository by lazy { AccessorisRepository(database.accessorisDao()) }
}