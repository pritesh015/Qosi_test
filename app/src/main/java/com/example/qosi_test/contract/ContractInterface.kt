package com.example.qosi_test.contract

import androidx.lifecycle.MutableLiveData
import com.example.qosi_test.models.ResponseUser
import com.example.qosi_test.models.ResponseUserDetail
import io.reactivex.Single

interface ContractInterface {
    interface View {
        fun updateList(userList: List<ResponseUser>)
        fun showError(error: String)
    }

    interface Presenter {
        fun getUserList()
        fun getNextUserList()
        fun userListLoaded()
        fun onError()
    }

    interface Model {
        fun getUserList()
        fun getNextUserList()
        fun onUserListLoaded(): MutableLiveData<List<ResponseUser>>
        fun onErrorLoaded(): MutableLiveData<String>
    }
}