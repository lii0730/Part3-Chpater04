package com.example.aop_part3_chpater04

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aop_part3_chpater04.adapters.BookRecyclerViewAdapter
import com.example.aop_part3_chpater04.api.BookService
import com.example.aop_part3_chpater04.databinding.ActivityMainBinding
import com.example.aop_part3_chpater04.model.BestSellerDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    private lateinit var adapter : BookRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBookRecyclerView()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://book.interpark.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val bookService = retrofit.create(BookService::class.java)

        bookService.getBestSellerBooks("A3F3C430E29896402AFABFCE15577962DC1A3BD07B128B5872C95FD3D82A7FD1")
            .enqueue(object : Callback<BestSellerDto> {
                override fun onResponse(
                    call: Call<BestSellerDto>,
                    response: Response<BestSellerDto>
                ) {
                    //TODO: API 요청 성공시
                    if (!response.isSuccessful) {
                        return
                    }
                    response.body()?.let {
                        it.books.forEach { book ->
                            Log.d(TAG, book.toString())
                        }
                        //TODO: adapter가 list로 체인지?
                        adapter.submitList(it.books)
                    }
                }

                override fun onFailure(call: Call<BestSellerDto>, t: Throwable) {
                    //TODO: API 요청 실패시
                }
            })
    }

    fun initBookRecyclerView () {
        adapter = BookRecyclerViewAdapter()
        binding.bookRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.bookRecyclerView.adapter = adapter
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}