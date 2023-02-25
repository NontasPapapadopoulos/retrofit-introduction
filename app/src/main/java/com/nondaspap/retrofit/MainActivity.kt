package com.nondaspap.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.nondaspap.retrofit.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private final val BASE_URL = "https://jsonplaceholder.typicode.com"
    lateinit var mainBinding: ActivityMainBinding
    private var posts: List<Post> = ArrayList<Post>()
    private lateinit var postsAdapter: PostsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        mainBinding.recyclerView.layoutManager = LinearLayoutManager(this)

        val view = mainBinding.root
        setContentView(view)

        fetchPosts()

    }

    private fun fetchPosts() {
        val retrofit = Retrofit.Builder()
                                .baseUrl(BASE_URL)
                                .addConverterFactory(GsonConverterFactory.create())
                                .build()


        val retrofitAPI: RetrofitAPI = retrofit.create(RetrofitAPI::class.java)

        val call: Call<List<Post>> = retrofitAPI.getAllPosts()

        call.enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {

                if(response.isSuccessful) {
                    posts = response.body() as ArrayList<Post>
                    initPostAdapter()
                    setRecyclerViewAdapter()
                    setRecyclerViewVisible()
                    removeProgressBar()
                }

            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                Toast.makeText(applicationContext, t.localizedMessage, Toast.LENGTH_LONG).show()
            }
        })
    }


    private fun initPostAdapter() {
        this.postsAdapter = PostsAdapter(posts as ArrayList<Post>)
    }

    private fun setRecyclerViewAdapter() {
        mainBinding.recyclerView.adapter = postsAdapter
    }

    private fun setRecyclerViewVisible() {
        mainBinding.recyclerView.visibility = VISIBLE
    }

    private fun removeProgressBar() {
        mainBinding.progressBar.visibility = GONE
    }

}