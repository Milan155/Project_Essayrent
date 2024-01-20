package com.example.essayrent.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.essayrent.Adapter.CategoryAdapter
import com.example.essayrent.Adapter.CategoryAdapter3
import com.example.essayrent.ApiInterface
import com.example.essayrent.Apiclient
import com.example.essayrent.Models.Cat_model
import com.example.essayrent.R
import com.example.essayrent.databinding.ActivityMaintenanceApplyBinding
import com.example.essayrent.databinding.ActivityMaintenanceReqBinding
import com.example.essayrent.databinding.ActivityViewCatBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class maintenance_req : AppCompatActivity() {

    private lateinit var binding: ActivityMaintenanceReqBinding
    lateinit var apiinterface: ApiInterface
    lateinit var list:MutableList<Cat_model>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMaintenanceReqBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        apiinterface=  Apiclient.getretrofit().create(ApiInterface::class.java)
        list = ArrayList()

        var layoutManager: RecyclerView.LayoutManager= GridLayoutManager(this,2)
        binding.maintanance.layoutManager=layoutManager

        var call: Call<List<Cat_model>> = apiinterface.categoryviewdata3()
        call.enqueue(object : Callback<List<Cat_model>> {
            override fun onResponse(call: Call<List<Cat_model>>, response: Response<List<Cat_model>>
            ) {
                Toast.makeText(applicationContext, "response", Toast.LENGTH_SHORT).show()
                list = response.body() as MutableList<Cat_model>

                var cadapter = CategoryAdapter3(applicationContext,list)
                binding.maintanance.adapter=cadapter

            }

            override fun onFailure(call: Call<List<Cat_model>>, t: Throwable) {
                Toast.makeText(applicationContext, "Error", Toast.LENGTH_SHORT).show()
            }
        })

    }
}