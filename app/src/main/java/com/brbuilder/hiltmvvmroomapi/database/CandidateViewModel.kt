package com.brbuilder.hiltmvvmroomapi.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.brbuilder.hiltmvvmroomapi.models.Candidate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CandidateViewModel
@Inject
constructor(private val candidateRepository: CandidateRepository) : ViewModel(){

    val getList: LiveData<List<Candidate>>
        get() =
            candidateRepository.getList.flowOn(Dispatchers.Main)
            .asLiveData(context = viewModelScope.coroutineContext)

    fun insert(candidate: Candidate){
        viewModelScope.launch {
            candidateRepository.insert(candidate)
        }
    }

    fun delete(candidate: Candidate){
        viewModelScope.launch {
            candidateRepository.delete(candidate)
        }
    }
}