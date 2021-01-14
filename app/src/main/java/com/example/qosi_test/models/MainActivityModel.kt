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
    private val list: MutableList<ResponseUser> = mutableListOf()
    private var userList: MutableLiveData<List<ResponseUser>> = MutableLiveData()
    private var liveError: MutableLiveData<String> = MutableLiveData()

    private val results = 10
    private var paging = 1
    private val lastPage = 5

    override fun getUserList() {
        paging = 1
        list.clear()

        userApiServiceImpl.getUser(results, paging)
            .run {
                subscribeBy(
                onSuccess = { response ->
                    response.map {
                        list.add(it)
                    }
                    userList.postValue(list)
                },
                onError = {
                    liveError.postValue("Error on get user list")
                }
            )}
    }

    override fun getNextUserList() {
        paging++

        if (paging > lastPage) {
            return
        }

        userApiServiceImpl.getUser(results, paging)
            .run {
                subscribeBy(
                    onSuccess = { response ->
                        response.map {
                            list.add(it)
                        }
                        userList.postValue(list)
                    },
                    onError = {
                        liveError.postValue("Error on get user list")
                    }
                )}
    }

    override fun onUserListLoaded(): MutableLiveData<List<ResponseUser>> {
        return userList
    }

    override fun getUserDetail(position: Int): ResponseUser {
        return list[position]
    }

    override fun onErrorLoaded(): MutableLiveData<String> {
        return liveError
    }
}