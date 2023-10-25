package com.example.findmyip.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.findmyip.model.IpState
import com.example.findmyip.repository.IpRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class IpViewModel(private val repository: IpRepository) : ViewModel() {
    private val _ipState = MutableStateFlow<IpState>(IpState.Empty)
    val ipState: StateFlow<IpState> get() = _ipState

    init {
        fetchIp()
    }

    private fun fetchIp(retryCount: Int = 0) {
        viewModelScope.launch {
            repository.fetchIp().collect {
                _ipState.value = it
            }
        }
    }
}

class IpViewModelFactory(private val repository: IpRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(IpViewModel::class.java)) {
            return IpViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}