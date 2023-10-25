package com.example.findmyip.repository

import com.example.findmyip.model.IpState
import com.example.findmyip.model.Network
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class IpRepository {

    suspend fun fetchIp(): Flow<IpState> {
        return flow {
            emit(IpState.Loading())

            val response = Network.api.getMyIp()
            if (response.isSuccessful) {
                response.body()?.let {
                    emit(IpState.Loading(false))
                    emit(IpState.Success(it))
                } ?: kotlin.run{
                    emit(IpState.Loading(false))
                    emit(IpState.Error(response.message()))
                }
            }
        }
    }
}