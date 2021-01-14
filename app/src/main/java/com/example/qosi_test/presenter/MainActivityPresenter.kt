package com.example.qosi_test.presenter

import androidx.lifecycle.LifecycleOwner
import com.example.qosi_test.contract.ContractInterface
import com.example.qosi_test.models.MainActivityModel
import com.example.qosi_test.models.ResponseUser

class MainActivityPresenter(view: ContractInterface.View, owner: LifecycleOwner): ContractInterface.Presenter {

    private var view: ContractInterface.View = view
    private var model: ContractInterface.Model = MainActivityModel()
    private val lifecycleOwner = owner

    override fun getUserList() {
        model.getUserList()
    }

    override fun getNextUserList() {
        model.getNextUserList()
    }

    override fun userListLoaded() {
        model.onUserListLoaded().observe(lifecycleOwner, {
            view.updateList(it)
        })
    }

    override fun getUserDetail(position: Int): ResponseUser {
        return model.getUserDetail(position)
    }

    override fun onError() {
        model.onErrorLoaded().observe(lifecycleOwner, {
            view.showError(it)
        })
    }
}