package com.brbuilder.hiltmvvmroomapi.repository

import com.brbuilder.hiltmvvmroomapi.models.CandidateResponseClass
import com.brbuilder.hiltmvvmroomapi.network.ApiServiceImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MainRepository
@Inject
constructor(private val apiServiceImpl: ApiServiceImpl) {

    fun getExpenseApiData(): Flow<CandidateResponseClass> = flow {
        emit(apiServiceImpl.getExpenseList())
    }.flowOn(Dispatchers.IO)
}