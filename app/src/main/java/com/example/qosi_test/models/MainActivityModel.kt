package com.example.qosi_test.models

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.qosi_test.api.UserApiService
import com.example.qosi_test.api.UserApiServiceImpl
import com.example.qosi_test.contract.ContractInterface
import io.reactivex.rxkotlin.subscribeBy

class MainActivityModel: ContractInterface.Model {

    private val userApiServiceImpl = UserApiServiceImpl()
    private var userList: MutableLiveData<List<ResponseUser>> = MutableLiveData()
    private var liveError: MutableLiveData<String> = MutableLiveData()

    private val results = 10
    private var paging = 1
    private val lastPage = 3

    override fun getUserList() {
        userApiServiceImpl.getUser(results, paging)
            .run {
                subscribeBy(
                onSuccess = {
                    userList.postValue(it.results)
                },
                onError = {
                    liveError.postValue("Error on get user list")
                }
            )}
    }

    override fun onUserListLoaded(): MutableLiveData<List<ResponseUser>> {
        return userList
    }
}