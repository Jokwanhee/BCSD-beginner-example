package com.example.chapter14

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chapter14.viewmodel.RepoViewModel
import com.example.chapter14.MainActivity.Companion.USER
import com.example.chapter14.adapter.RepoAdapter
import com.example.chapter14.databinding.ActivityRepoBinding
import com.example.chapter14.model.Repo
import com.example.chapter14.model.User
import com.example.chapter14.network.GithubService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRepoBinding
    private val viewModel by lazy {
        ViewModelProvider(this)
            .get(RepoViewModel::class.java)
    }
    private lateinit var repoAdapter: RepoAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager

    private var page = 1
    private var hasMore = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRepoBinding.inflate(layoutInflater).also {
            setContentView(it.root)
            it.vm = viewModel
        }

        getUser()
        initAdapter()
        initRecyclerView()
        scrollRecyclerView()
        listRepo(viewModel.username.value.toString(), 0)
    }


    private fun getUser() {
        val user = intent.getParcelableExtra(USER) as? User
//        val user = intent.getParcelableExtra("user", User::class.java)
        viewModel.setUsername(user?.username ?: return)
    }

    private fun initAdapter() {
        repoAdapter = RepoAdapter { repo ->
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(repo.htmlUrl))
            startActivity(intent)
        }
    }

    private fun initRecyclerView() {
        linearLayoutManager = LinearLayoutManager(this)
        binding.repoRecyclerView.also {
            it.layoutManager = linearLayoutManager
            it.adapter = repoAdapter
        }
    }

    private fun scrollRecyclerView() {
        binding.repoRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val totalCount = linearLayoutManager.itemCount
                val lastVisiblePosition =
                    linearLayoutManager.findLastCompletelyVisibleItemPosition()

                if (lastVisiblePosition >= (totalCount - 1) && hasMore) {
                    page += 1
                    listRepo(viewModel.username.value.toString(), page)
                }
            }
        })
    }

    private fun listRepo(path: String, page: Int) {
        val githubService = ApiClient.retrofit.create(GithubService::class.java)
        binding.progressBar.isVisible = true

        githubService.listRepos(path, page).enqueue(object : Callback<List<Repo>> {
            override fun onResponse(call: Call<List<Repo>>, response: Response<List<Repo>>) {
                binding.progressBar.isVisible = false
                hasMore = response.body()?.count() == 30 // 페이지를 구현하기 위해서 한 페이지 당 오는 기본 페이지 수는 30이기 때문에
                repoAdapter.submitList(repoAdapter.currentList + response.body().orEmpty())
            }

            override fun onFailure(call: Call<List<Repo>>, t: Throwable) {
                t.printStackTrace()
                Toast.makeText(this@RepoActivity, "에러", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }
}