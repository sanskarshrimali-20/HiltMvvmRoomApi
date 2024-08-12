package com.brbuilder.hiltmvvmroomapi.models

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "candidate")
data class Candidate(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    val id: Int,
    val employee_age: Int,
    val employee_name: String,
    val employee_salary: Int,
    val profile_image: String
)