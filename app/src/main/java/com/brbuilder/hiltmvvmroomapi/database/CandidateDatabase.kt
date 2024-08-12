package com.brbuilder.hiltmvvmroomapi.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.brbuilder.hiltmvvmroomapi.models.Candidate


@Database(entities = [Candidate::class], version = 1, exportSchema = false)
abstract class CandidateDatabase : RoomDatabase(){
    abstract fun candidateDao(): CandidateDao

}