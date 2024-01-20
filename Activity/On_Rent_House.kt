package com.example.essayrent.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.essayrent.Adapter.house_rentcat
import com.example.essayrent.ApiInterface
import com.example.essayrent.Apiclient
import com.example.essayrent.Models.On_house_model
import com.example.essayrent.databinding.ActivityOnRentHouseBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class On_Rent_House : AppCompatActivity() {

    lateinit var binding: ActivityOnRentHouseBinding
    lateinit var apiinterface: ApiInterface
    lateinit var list: MutableList<On_house_model>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnRentHouseBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        var layoutManager: RecyclerView.LayoutManager= LinearLayoutManager(this)
        binding.recycle2.layoutManager=layoutManager

        apiinterface=Apiclient.getretrofit().create(ApiInterface::class.java)
        list=ArrayList()

        var call: Call<List<On_house_model>> = apiinterface.gethouserent()
        call.enqueue(object : Callback<List<On_house_model>> {
            override fun onResponse(call: Call<List<On_house_model>>, response: Response<List<On_house_model>>)
            {
                Toast.makeText(applicationContext, "done", Toast.LENGTH_SHORT).show()

                list = response.body() as MutableList<On_house_model>
                var cadapter = house_rentcat(applicationContext,list)
                binding.recycle2.adapter=cadapter


            }

            override fun onFailure(call: Call<List<On_house_model>>, t: Throwable) {
                Toast.makeText(applicationContext, "not done", Toast.LENGTH_SHORT).show()
            }


        })

}
}