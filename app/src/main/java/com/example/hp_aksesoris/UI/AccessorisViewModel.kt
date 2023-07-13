package com.example.hp_aksesoris.UI

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.hp_aksesoris.model.Accessoris
import com.example.hp_aksesoris.repository.AccessorisRepository
import kotlinx.coroutines.launch
import kotlin.IllegalArgumentException

class AccessorisViewModel(private val repository: AccessorisRepository): ViewModel(){
    val allAccessoris: LiveData<List<Accessoris>> = repository.allAccessories.asLiveData()

    fun insert(accessoris: Accessoris) = viewModelScope.launch {
        repository.insertAccessoris(accessoris)
    }

    fun delete(accessoris: Accessoris) = viewModelScope.launch {
        repository.deleteAccessoris(accessoris)
    }

    fun update(accessoris: Accessoris) = viewModelScope.launch {
        repository.updateAccessoris(accessoris)
    }
}

class AccessorisViewModelFactory(private val repository: AccessorisRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom((AccessorisViewModel::class.java))){
            return AccessorisViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}