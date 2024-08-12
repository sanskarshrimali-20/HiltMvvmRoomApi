package com.brbuilder.hiltmvvmroomapi.network

import com.brbuilder.hiltmvvmroomapi.models.CandidateResponseClass
import com.brbuilder.hiltmvvmroomapi.network.ApiService
import javax.inject.Inject

class ApiServiceImpl @Inject constructor(private val apiService: ApiService) {

    suspend fun getExpenseList(): CandidateResponseClass = apiService.getExpenseLists()

}