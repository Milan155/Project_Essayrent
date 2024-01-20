package com.example.essayrent.Activity
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import com.example.essayrent.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {


    lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        //if net connection is on app will be run otherwise app will be destroy
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        if (activeNetwork == null || !activeNetwork.isConnectedOrConnecting) {
            // If the network connection is not available, close the app
            Toast.makeText(this, "No internet connection", Toast.LENGTH_SHORT).show()
            finish()
        }


        Handler().postDelayed(Runnable {
            var i=Intent(applicationContext, Registration::class.java)
            startActivity(i)
        },3000)

    }


}