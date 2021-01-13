package com.example.qosi_test.contract

import androidx.lifecycle.MutableLiveData
import com.example.qosi_test.models.ResponseUser
import com.example.qosi_test.models.ResponseUserDetail
import io.reactivex.Single

interface ContractInterface {
    interface View {
        fun initView()
        fun updateList(userList: List<ResponseUser>)
    }

    interface Presenter {
        fun getUserList()
    }

    interface Model {
        fun getUserList()
        fun onUserListLoaded(): MutableLiveData<List<ResponseUser>>
    }
}