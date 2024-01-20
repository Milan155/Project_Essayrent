package com.example.essayrent.Activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.essayrent.ApiInterface
import com.example.essayrent.Apiclient
import com.example.essayrent.Models.Model
import com.example.essayrent.databinding.ActivityLoginCodeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Login_Code : AppCompatActivity() {

    lateinit var binding: ActivityLoginCodeBinding
    lateinit var apiInterface: ApiInterface
    var m: Model = Model()
    lateinit var sharedPreferenceUser: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginCodeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        apiInterface= Apiclient.getretrofit().create(ApiInterface::class.java)
        //(user_session) id is use name to save this id
        sharedPreferenceUser=getSharedPreferences("USER_SESSION",Context.MODE_PRIVATE)
        if (sharedPreferenceUser.getBoolean("USER_SESSION", false) && !sharedPreferenceUser.getString("phone", "")!!.isEmpty()
        ) {
            startActivity(Intent(this, Rental_Dashboard::class.java))
            finish()
        }
        binding.forgotPassword.setOnClickListener {
            startActivity(Intent(this, forgot_password::class.java))
            finish()
        }

       binding.btnLogIn.setOnClickListener {

           var phone=binding.edtPhone.text.toString()
           var pass=binding.edtPassword.text.toString()

           var call:Call<Void> = apiInterface.logindata(phone,pass)
           call.enqueue(object : Callback<Void> {
               override fun onResponse(call: Call<Void>, response: Response<Void>) {
                   Toast.makeText(applicationContext, "Login done", Toast.LENGTH_SHORT).show()
                   var editor: SharedPreferences.Editor = sharedPreferenceUser.edit()
                   editor.putBoolean("USER_SESSION", true)
                   //this session is match field and go to rental deeshboard
                   editor.putString("phone", phone)
                   editor.putString("Password", pass)
                   editor.commit()
                   var i=Intent(applicationContext, Rental_Dashboard::class.java)
                   startActivity(i)
               }

               override fun onFailure(call: Call<Void>, t: Throwable) {
                   Toast.makeText(applicationContext, "Login fail", Toast.LENGTH_SHORT).show()
               }

           })
       }



    }
}