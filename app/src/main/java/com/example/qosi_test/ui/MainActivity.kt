package com.example.qosi_test.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.qosi_test.R
import com.example.qosi_test.contract.ContractInterface
import com.example.qosi_test.databinding.ActivityMainBinding
import com.example.qosi_test.models.ResponseUser
import com.example.qosi_test.presenter.MainActivityPresenter
import com.example.qosi_test.ui.adapter.MainActivityAdapter

class MainActivity : AppCompatActivity(), ContractInterface.View {
    
    private var presenter: MainActivityPresenter? = null
    private lateinit var userDetailAdapter: MainActivityAdapter

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        
        presenter = MainActivityPresenter(this, this)

        presenter!!.getUserList()

        setAdapter()
    }

    private fun setAdapter() {
        userDetailAdapter = MainActivityAdapter(this, ArrayList())

        binding.rvUser.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvUser.adapter = userDetailAdapter
    }

    override fun updateList(userList: List<ResponseUser>) {
        userDetailAdapter.setList(userList)
    }

}