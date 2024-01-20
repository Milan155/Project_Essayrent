package com.example.essayrent.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.essayrent.Adapter.house_rentview
import com.example.essayrent.ApiInterface
import com.example.essayrent.Apiclient
import com.example.essayrent.Models.On_house_model
import com.example.essayrent.databinding.ActivityRentDetailsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class rent_details : AppCompatActivity() {

    lateinit var binding: ActivityRentDetailsBinding
    lateinit var apiinterface: ApiInterface
    lateinit var list: MutableList<On_house_model>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRentDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        var layoutManager: RecyclerView.LayoutManager= LinearLayoutManager(this)
        binding.recycleview3.layoutManager=layoutManager

        apiinterface= Apiclient.getretrofit().create(ApiInterface::class.java)
        list=ArrayList()


        var i = intent
        var id = i.getIntExtra("mypos",102)


        var call: Call<List<On_house_model>> = apiinterface.gethouserentc(id)
        call.enqueue(object : Callback<List<On_house_model>> {
            override fun onResponse(call: Call<List<On_house_model>>, response: Response<List<On_house_model>>)
            {
                Toast.makeText(applicationContext, "done", Toast.LENGTH_SHORT).show()

                list = response.body() as MutableList<On_house_model>
                var cadapter = house_rentview(applicationContext,list)
                binding.recycleview3.adapter=cadapter

            }

            override fun onFailure(call: Call<List<On_house_model>>, t: Throwable) {
                Toast.makeText(applicationContext, "not done", Toast.LENGTH_SHORT).show()
            }

        })



    }
}