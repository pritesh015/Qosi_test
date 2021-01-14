package com.example.qosi_test.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
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

        setAdapter()
        presenter!!.userListLoaded()
        presenter!!.onError()
    }

    private fun setAdapter() {
        userDetailAdapter = MainActivityAdapter(this, ArrayList())

        binding.rvUser.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvUser.adapter = userDetailAdapter

        presenter!!.getUserList()

        userDetailAdapter.listener = object: MainActivityAdapter.Listener {
            override fun onBottomReached(position: Int) {
                presenter!!.getNextUserList()
            }

            override fun onItemClicked(position: Int) {
                val userDialog = UserDetailDialog()
                val user: ResponseUser = presenter!!.getUserDetail(position)
                var name = user.name?.last
                name = name.plus(" ")
                name = name.plus(user.name?.first)

                val args = Bundle()
                args.putString("email", user.email)
                args.putString("name", name)
                args.putString("picture", user.picture?.largePicture)
                userDialog.arguments = args
                userDialog.show(supportFragmentManager, "UserDetailDialog")
            }
        }
    }

    override fun updateList(userList: List<ResponseUser>) {
        userDetailAdapter.setList(userList.distinctBy { it.name?.last })
    }

    override fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

}