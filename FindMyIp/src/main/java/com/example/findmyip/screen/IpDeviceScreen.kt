package com.example.findmyip.screen

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.example.findmyip.model.IpResponse
import com.example.findmyip.model.IpState
import com.example.findmyip.viewmodel.IpViewModel

private const val TAG = "IPDeviceScreen"

@Composable
fun DisplayIp(viewModel: IpViewModel) {
    val ipState = viewModel.ipState.collectAsState().value
    Log.d("TAG", "DisplayIp: $ipState")
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        when (ipState) {
            is IpState.Loading -> {
                CircularProgressIndicator()
            }
            is IpState.Success -> {
                DisplaySuccess(ipResponse = ipState.ipResponse)
            }
            is IpState.Error -> DisplayError(ipState.message)
            else -> {}
        }
    }
}

@Composable
fun DisplayError(errorMessage: String) {
    Text(text = errorMessage, color = Color.Red, textAlign = TextAlign.Center)
}

@Composable
fun DisplaySuccess(ipResponse: IpResponse) {
    Column {
        Text(text = "Your City: ${ipResponse.city}", textAlign = TextAlign.Center)
        Text(text = "Your Country: ${ipResponse.country_name}", textAlign = TextAlign.Center)
        Text(text = "Your Region: ${ipResponse.region}", textAlign = TextAlign.Center)
    }
}