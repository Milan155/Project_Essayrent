package com.example.essayrent.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.essayrent.Adapter.CategoryAdapter2
import com.example.essayrent.Adapter.CategoryAdapter3
import com.example.essayrent.Adapter.house_rentcat
import com.example.essayrent.ApiInterface
import com.example.essayrent.Apiclient
import com.example.essayrent.Models.Cat_model
import com.example.essayrent.Models.On_house_model
import com.example.essayrent.R
import com.example.essayrent.databinding.ActivityOnRentHouseBinding
import com.example.essayrent.databinding.ActivityPropertyTaxBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Property_tax : AppCompatActivity() {


    lateinit var binding: ActivityPropertyTaxBinding
    lateinit var apiinterface: ApiInterface
    lateinit var list: MutableList<Cat_model>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPropertyTaxBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        var layoutManager: RecyclerView.LayoutManager= GridLayoutManager(this,2)
        binding.recycle4.layoutManager=layoutManager

        apiinterface= Apiclient.getretrofit().create(ApiInterface::class.java)
        list=ArrayList()

        var call: Call<List<Cat_model>> = apiinterface.getpropertyview()
        call.enqueue(object : Callback<List<Cat_model>> {
            override fun onResponse(call: Call<List<Cat_model>>, response: Response<List<Cat_model>>)
            {
                Toast.makeText(applicationContext, "done", Toast.LENGTH_SHORT).show()

                list = response.body() as MutableList<Cat_model>
                var cadapter = CategoryAdapter2(applicationContext,list)
                binding.recycle4.adapter=cadapter
            }
            override fun onFailure(call: Call<List<Cat_model>>, t: Throwable) {
                Toast.makeText(applicationContext, "not done", Toast.LENGTH_SHORT).show()
            }
        })


    }
}