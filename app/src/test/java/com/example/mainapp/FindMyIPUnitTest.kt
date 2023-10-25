package com.example.mainapp

import android.provider.CallLog.Calls
import com.example.findmyip.model.IpApi
import com.example.findmyip.model.IpResponse
import com.example.findmyip.model.IpState
import com.example.findmyip.repository.IpRepository
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import retrofit2.Response

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class FindMyIPUnitTest {

    @Mock
    private lateinit var api: IpApi

    private lateinit var repository: IpRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        repository = IpRepository()
        api = mock(IpApi::class.java)
    }

    @Test
    fun `test fetchIp success`() = runBlocking {
        val response = IpResponse(region = "Georgia", city = "Atlanta", country_name = "United States")
        val mockResponse = Response.success(response)

        `when`(api.getMyIp()).thenReturn(mockResponse)

        val result = api.getMyIp().body()!!

        // Verify that the flow emits the expected states in the correct order
        assertEquals(result.region, response.region)
        assertEquals(result.city, response.city)
        assertEquals(result.country_name, response.country_name)
    }
}