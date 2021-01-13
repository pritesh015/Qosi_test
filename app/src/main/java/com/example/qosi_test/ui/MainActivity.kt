package com.example.qosi_test.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.qosi_test.R
import com.example.qosi_test.contract.ContractInterface
import com.example.qosi_test.models.ResponseUser
import com.example.qosi_test.presenter.MainActivityPresenter

class MainActivity : AppCompatActivity(), ContractInterface.View {
    
    private var presenter: MainActivityPresenter? = null
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        presenter = MainActivityPresenter(this, this)

        presenter!!.getUserList()
    }

    override fun initView() {
        println("INIT")
    }

    override fun updateList(userList: List<ResponseUser>) {
        println(userList)
    }

}