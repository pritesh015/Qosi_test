package com.example.qosi_test.presenter

import android.app.Activity
import androidx.lifecycle.LifecycleOwner
import com.example.qosi_test.contract.ContractInterface
import com.example.qosi_test.models.MainActivityModel

class MainActivityPresenter(view: ContractInterface.View, owner: LifecycleOwner): ContractInterface.Presenter {

    private var view: ContractInterface.View = view
    private var model: ContractInterface.Model = MainActivityModel()
    private val lifecycleOwner = owner

    init {
        view.initView()
    }

    override fun getUserList() {
        model.getUserList()
        model.onUserListLoaded().observe(lifecycleOwner, {
            view.updateList(it)
        })
    }



}