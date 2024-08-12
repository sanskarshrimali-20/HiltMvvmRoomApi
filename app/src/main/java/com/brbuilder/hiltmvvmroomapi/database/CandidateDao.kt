package com.brbuilder.hiltmvvmroomapi.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.brbuilder.hiltmvvmroomapi.models.Candidate
import kotlinx.coroutines.flow.Flow

@Dao
interface CandidateDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: Candidate)

    @Query("SELECT * FROM candidate ORDER BY id ASC")
    fun getList(): Flow<List<Candidate>>

    @Delete
    fun delete(alert: Candidate)
}