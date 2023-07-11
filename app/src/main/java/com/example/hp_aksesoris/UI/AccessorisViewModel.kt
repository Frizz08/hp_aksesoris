package com.example.hp_aksesoris.UI

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.hp_aksesoris.model.Accessoris
import com.example.hp_aksesoris.repository.AccessorisRepository

class AccessorisViewModel(private val repository: AccessorisRepository): ViewModel(){
    val allAccessoris: LiveData<List<Accessoris>> = repository.allAccessories.asLiveData()
}