package com.example.essayrent.Activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.daimajia.slider.library.SliderLayout
import com.daimajia.slider.library.SliderTypes.TextSliderView
import com.example.essayrent.Adapter.DashbaordAdapter
import com.example.essayrent.ApiInterface
import com.example.essayrent.Apiclient
import com.example.essayrent.Models.Dashboardmodel
import com.example.essayrent.R
import com.example.essayrent.databinding.ActivityRentalDashboardBinding
import retrofit2.Callback
import retrofit2.Response

class Rental_Dashboard : AppCompatActivity() {
    lateinit var binding: ActivityRentalDashboardBinding
    lateinit var sharedPreferences: SharedPreferences
    lateinit var sharedPreferencesUser: SharedPreferences
    lateinit var sliderlayout: SliderLayout
    var map = HashMap<String,Int>()
    lateinit var list: MutableList<Dashboardmodel>
    lateinit var apiinterface: ApiInterface


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRentalDashboardBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setSupportActionBar(binding.tool)

        // in login file (Session) name is use for save id
        sharedPreferences=getSharedPreferences("SESSION", Context.MODE_PRIVATE)
        sharedPreferencesUser=getSharedPreferences("USER_SESSION", Context.MODE_PRIVATE)

        sliderlayout = findViewById(R.id.slider)
        apiinterface= Apiclient.getretrofit().create(ApiInterface::class.java)
        list=ArrayList()

        var layoutManager: RecyclerView.LayoutManager = GridLayoutManager(this,2)
        binding.viewpage.layoutManager=layoutManager

        map.put("Rental",R.drawable.ok)
        map.put("Sell your property",R.drawable.ok2)
        map.put("Find rental person",R.drawable.ok3)
        map.put("Rent your house",R.drawable.ok4)

        for(i in map.keys)
        {
            var textslider = TextSliderView(this)
            textslider.description(i)
            textslider.image(map.get(i)!!)
            sliderlayout.addSlider(textslider)
        }
        //Present the slide view
        sliderlayout.setPresetTransformer(SliderLayout.Transformer.ZoomIn)

        var call: retrofit2.Call<List<Dashboardmodel>> =  apiinterface.categoryviewdata()
        call.enqueue(object: Callback<List<Dashboardmodel>> {
            override fun onResponse(call: retrofit2.Call<List<Dashboardmodel>>, response: Response<List<Dashboardmodel>>)
            {
                Toast.makeText(applicationContext,"Ok ", Toast.LENGTH_LONG).show()
                list = response.body() as MutableList<Dashboardmodel>
                var myAdapter = DashbaordAdapter(applicationContext,list)
                binding.viewpage.adapter=myAdapter
            }
            override fun onFailure(call: retrofit2.Call<List<Dashboardmodel>>, t: Throwable) {
                Toast.makeText(applicationContext, "Fail", Toast.LENGTH_SHORT).show()
            }

        })
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId) {
            R.id.option1 ->
            {
               // sharedPreferences.edit().clear().commit()
                sharedPreferencesUser.edit().clear().commit()
                finish()
                startActivity(Intent(applicationContext, Login_Code::class.java))
            }

        }
        when(item.itemId)
        {
            R.id.profileview ->
            {
                //profile view of register user
                var i=Intent(applicationContext, Profile_View::class.java)
                startActivity(i)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        finishAffinity()
        super.onBackPressed()
    }
}
