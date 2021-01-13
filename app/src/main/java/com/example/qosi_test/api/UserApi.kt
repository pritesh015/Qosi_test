package com.example.qosi_test.api

import com.example.qosi_test.Constants
import com.example.qosi_test.models.ResponseUser
import com.example.qosi_test.models.ResponseUserDetail
import com.squareup.moshi.Moshi
import io.reactivex.Single
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApi {

    @GET("api/")
    fun getUser(@Query("results") results: Int, @Query("page") page: Int): Single<ResponseUserDetail>

    companion object Factory {
        fun create(): UserApi {
            val moshi = Moshi.Builder()
                .build()
            val retrofit = retrofit2.Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
                .baseUrl(Constants.BASE_URL)
                .build()

            return retrofit.create(UserApi::class.java)
        }
    }

}