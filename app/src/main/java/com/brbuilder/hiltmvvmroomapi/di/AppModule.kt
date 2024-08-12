package com.brbuilder.hiltmvvmroomapi.di

import android.content.Context
import androidx.room.Room
import com.brbuilder.hiltmvvmroomapi.constant.Cons.BASE_URL
import com.brbuilder.hiltmvvmroomapi.database.CandidateDao
import com.brbuilder.hiltmvvmroomapi.database.CandidateDatabase
import com.brbuilder.hiltmvvmroomapi.database.CandidateRepository
import com.brbuilder.hiltmvvmroomapi.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun providesAlertDao(userDatabase: CandidateDatabase): CandidateDao = userDatabase.candidateDao()

    @Provides
    @Singleton
    fun providesAlertDatabase(@ApplicationContext context: Context): CandidateDatabase
            = Room.databaseBuilder(context, CandidateDatabase::class.java,"AlertDatabase").allowMainThreadQueries().build()

    @Provides
    fun providesUserRepository(userDao: CandidateDao) : CandidateRepository
            = CandidateRepository(userDao)

    @Provides
    fun providesUrlTest() = BASE_URL

    // Create a logging interceptor
    val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Singleton
    @Provides
    fun providesOkHttpClient(): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(logging)
//            .addInterceptor(BasicAuthInterceptor("PortOfOakland", "OaklandPortal@2022!*"))
            .build()

    @Provides
    @Singleton
    fun providesApiService( url: String): ApiService =
        Retrofit.Builder()
            .baseUrl(url)
            .client(providesOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
}