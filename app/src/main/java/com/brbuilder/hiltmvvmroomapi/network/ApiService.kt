package com.brbuilder.hiltmvvmroomapi.network

import com.brbuilder.hiltmvvmroomapi.constant.Cons
import com.brbuilder.hiltmvvmroomapi.models.CandidateResponseClass
import retrofit2.http.GET

interface ApiService {

    @GET(Cons.EMPLOYEES)
    suspend fun getExpenseLists(): CandidateResponseClass
}