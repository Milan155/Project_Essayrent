package com.example.essayrent.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.essayrent.R
import com.example.essayrent.databinding.ActivityForgotPasswordBinding

class forgot_password : AppCompatActivity() {


    private lateinit var binding: ActivityForgotPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        var i = intent
        var id = i.getIntExtra("id",101)
        binding.edtnumber.setText(i.getStringExtra("Phone"))
        binding.edtpass.setText(i.getStringExtra("Password"))

        binding.btnupdate.setOnClickListener {

            var Phone = binding.edtnumber.text.toString()
            var Password = binding.edtpass.text.toString()


            var stringRequest: StringRequest = object : StringRequest
                (
                Request.Method.POST,"https://unaffecting-firearm.000webhostapp.com/Essayrental-Project/forgot.php",
                Response.Listener {
                    Toast.makeText(applicationContext,"Product Updated", Toast.LENGTH_LONG).show()
                    startActivity(Intent(applicationContext,Login_Code::class.java))
                },
                {
                    Toast.makeText(applicationContext,"No Internet",Toast.LENGTH_LONG).show()
                })
            {
                override fun getParams(): MutableMap<String, String>?
                {
                    var map = HashMap<String,String>()
                    map["id"]=id.toString()
                    map["Phone"]=Phone
                    map["Password"]=Password
                    return map
                }
            }
            var queue: RequestQueue = Volley.newRequestQueue(this)
            queue.add(stringRequest)
        }
    }
}
