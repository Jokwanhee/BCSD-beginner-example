package com.example.chapter14

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chapter14.adapter.UserAdapter
import com.example.chapter14.databinding.ActivityMainBinding
import com.example.chapter14.model.UserDTO
import com.example.chapter14.network.GithubService
import com.example.chapter14.viewmodel.MainViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel by lazy {
        ViewModelProvider(this)
            .get(MainViewModel::class.java)
    }
    private lateinit var userAdapter: UserAdapter

    private val handler = Handler(Looper.getMainLooper())
    var searchFor = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
            it.vm = viewModel
        }
        initAdapter()
        initRecyclerView()
        setEditText()
    }

    private fun initAdapter() {
        userAdapter = UserAdapter { user ->
            val intent = Intent(this, RepoActivity::class.java)
            intent.putExtra(USER, user)
            startActivity(intent)
        }
    }

    private fun initRecyclerView() {
        binding.userRecyclerView.also {
            it.layoutManager = LinearLayoutManager(this)
            it.adapter = userAdapter
        }
    }

    private fun setEditText() {
        /** 디바운싱 처리 */
        val runnable = Runnable {
            searchUser()
        }

        binding.searchEditText.addTextChangedListener {
//            searchFor = it.toString()
            viewModel.searchText.value = it.toString()

            handler.removeCallbacks(runnable)
            handler.postDelayed(
                runnable,
                300
            )
        }

        binding.textField.setEndIconOnClickListener {
            // todo:: handle EditText Icon click
        }
    }

    private fun searchUser() {
        val githubService = ApiClient.retrofit.create(GithubService::class.java)
        Log.d("로그" ,"searchUser ${viewModel.searchText.value}")

        githubService.searchUsers("a")
            .enqueue(object : Callback<UserDTO> {
                override fun onResponse(call: Call<UserDTO>, response: Response<UserDTO>) {
                    Log.d("로그" ,"searchUsers onResponse")
                    userAdapter.submitList(response.body()?.items)
                }

                override fun onFailure(call: Call<UserDTO>, t: Throwable) {
                    Log.d("로그" ,"searchUsers onFailure")
                    t.printStackTrace()
                    Toast.makeText(this@MainActivity, getString(R.string.error), Toast.LENGTH_SHORT)
                        .show()
                }
            })
    }

    companion object {
        const val USER = "user"
    }
}