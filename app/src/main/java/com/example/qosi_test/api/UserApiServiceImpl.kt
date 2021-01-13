package com.example.qosi_test.api

import android.util.Log
import com.example.qosi_test.models.ResponseUserDetail
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class UserApiServiceImpl: UserApiService {

    private var userApi: UserApi = UserApi.create()
    private val LOGTAG = "UserApiServiceImpl"

    override fun getUser(result: Int, page: Int): Single<ResponseUserDetail> =
        Single.create { emitter ->
            userApi.getUser(result, page)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeBy(
                    onSuccess = { response ->
                        emitter.onSuccess(response)
                    },
                    onError = {
                        Log.e(LOGTAG, it.localizedMessage ?: "")
                        emitter.onError(it)
                    }
                )
    }
}