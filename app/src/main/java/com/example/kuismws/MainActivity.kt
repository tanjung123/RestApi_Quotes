package com.example.kuismws

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    //menambahkan Adapter RecycleView nya
    private lateinit var adapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //deklarasikan adapter ke Activity
        adapter = MyAdapter()
        //untuk memberitahukan adapter jika ada data yang berubah
        adapter.notifyDataSetChanged()

        //recycleView di activity_main.xml sudah dideklarasikan menggunakan kotlinx.android.synthetic
        //setting layout recycleview ke LinearLayout
        rvQuotes.layoutManager = LinearLayoutManager(this)
        //menambahkan MyAdapter sebagai adapter dari RecycleView
        rvQuotes.adapter = adapter

        //menampilkan progressBar
        showLoading(true)

        //untuk melakukan request REST API menggunakan library Fast Android Networking dan menambahkannya ke adapter
        getQuotes()

    }

    private fun getQuotes() {
        var listQuotes = ArrayList<Quotes>()
        val retro = Retro().getRetroClientInstance("https://type.fit/").create(QuotesAPI::class.java)
        retro.getQuotes().enqueue(object : Callback<List<Quotes>> {
            override fun onFailure(call: Call<List<Quotes>>, t: Throwable) {
                Log.e("Failed", t.message.toString())
            }
            override fun onResponse(call: Call<List<Quotes>>, response: Response<List<Quotes>>) {
                    Log.e("_kotlinResponse", response.toString())

                    val quotes = response.body()
                    for (i in 0 until quotes!!.count()) {
                        val jsonObject = quotes!!.get(i)
                        val itemQuotes = Quotes()
                        itemQuotes.text = jsonObject.text
                        itemQuotes.author = jsonObject.author
                        listQuotes.add(itemQuotes)
                    }
                    adapter.setData(listQuotes)
                    showLoading(false)
                }
            })
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }
}