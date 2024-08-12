package com.brbuilder.hiltmvvmroomapi.database

import androidx.annotation.WorkerThread
import com.brbuilder.hiltmvvmroomapi.models.Candidate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CandidateRepository @Inject constructor (private val alertDao: CandidateDao) {

    val getList: Flow<List<Candidate>> = alertDao.getList()

    @WorkerThread
    suspend fun insert(alert: Candidate) = withContext(Dispatchers.IO){
        alertDao.insert(alert)
    }

    @WorkerThread
    suspend fun delete(alert: Candidate) = withContext(Dispatchers.IO){
        alertDao.delete(alert)
    }

}