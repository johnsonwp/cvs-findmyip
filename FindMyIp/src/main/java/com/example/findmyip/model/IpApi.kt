package com.example.findmyip.model

import retrofit2.Response
import retrofit2.http.GET

interface IpApi {
    @GET(Constants.ENDPOINT)
    suspend fun getMyIp(): Response<IpResponse>
}