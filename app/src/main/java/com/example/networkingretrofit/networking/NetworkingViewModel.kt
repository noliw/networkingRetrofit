package com.example.networkingretrofit.networking

import androidx.lifecycle.ViewModel
import javax.inject.Inject

class NetworkingViewModel @Inject constructor(
    private val networkingRepository: NetworkingRepository
): ViewModel() {

}