package com.brbuilder.hiltmvvmroomapi.models

import com.brbuilder.hiltmvvmroomapi.models.Candidate

data class CandidateResponseClass(
    val `data`: List<Candidate>,
    val message: String,
    val status: String
)