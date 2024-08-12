package com.brbuilder.hiltmvvmroomapi.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brbuilder.hiltmvvmroomapi.repository.MainRepository
import com.brbuilder.hiltmvvmroomapi.util.ApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CandidateListViewModel @Inject
constructor(private val mainRepository: MainRepository) : ViewModel() {

    val postStateFlow: MutableStateFlow<ApiState> = MutableStateFlow(ApiState.Empty)

    fun getExpenseListItem() = viewModelScope.launch {
        postStateFlow.value = ApiState.Loading
        mainRepository.getExpenseApiData()
            .catch { e ->
                postStateFlow.value = ApiState.Failure(e)
            }.collect { data ->
                postStateFlow.value = ApiState.Success(data)
            }
    }
}