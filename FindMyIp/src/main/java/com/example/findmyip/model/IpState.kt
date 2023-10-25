package com.example.findmyip.model

sealed class IpState {
    data class Loading(val isLoading: Boolean = true) : IpState()
    data class Success(val ipResponse: IpResponse) : IpState()
    data class Error(val message: String) : IpState()
    object Empty: IpState()
}