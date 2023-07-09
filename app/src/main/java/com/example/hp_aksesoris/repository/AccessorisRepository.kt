package com.example.hp_aksesoris.repository

import com.example.hp_aksesoris.dao.AccessorisDao
import com.example.hp_aksesoris.model.Accessoris
import kotlinx.coroutines.flow.Flow

class AccessorisRepository(private val accessorisDao: AccessorisDao) {
    val allAccessories: Flow<List<Accessoris>> = accessorisDao.getAllAccessoris()
    suspend fun insertAccessoris(accessoris: Accessoris){
        accessorisDao.insertAccessoris(accessoris)
    }
    suspend fun deleteAccessoris(accessoris: Accessoris){
        accessorisDao.deleteAccessoris(accessoris)
    }
    suspend fun updateAccessoris(accessoris: Accessoris){
        accessorisDao.updateAccessoris(accessoris)
    }
}