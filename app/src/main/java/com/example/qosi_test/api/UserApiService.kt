package com.example.qosi_test.api

import com.example.qosi_test.models.ResponseUser
import com.example.qosi_test.models.ResponseUserDetail
import io.reactivex.Single

interface UserApiService {
    fun getUser(result: Int, page: Int): Single<List<ResponseUser>>
}