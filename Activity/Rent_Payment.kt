package com.example.essayrent.Activity

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.essayrent.Models.On_house_model
import com.example.essayrent.R
import com.example.essayrent.databinding.ActivityRentDetailsBinding
import com.example.essayrent.databinding.ActivityRentPaymentBinding
import com.razorpay.Checkout
import com.squareup.picasso.Picasso
import org.json.JSONException
import org.json.JSONObject

class Rent_Payment : AppCompatActivity() {

    private lateinit var binding: ActivityRentPaymentBinding
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRentPaymentBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        val i = intent
        val id = i.getIntExtra("id", 0)
        val description = i.getStringExtra("description")
        val price = i.getStringExtra("price")
        val image = i.getStringExtra("image")
        sharedPreferences = getSharedPreferences("Payment", Context.MODE_PRIVATE)


        binding.giftDesc.text = description
        binding.giftPrice.text = price.toString()
        Picasso.with(applicationContext).load(image).into(binding.imageView)

        binding.makePaymentBtn.setOnClickListener {
            var finaldata=price

            Log.d("rentprice",finaldata.toString())

            val pPrice = Integer.parseInt(price.toString()) * 100
            val checkout = Checkout()
            checkout.setKeyID("rzp_test_lIfFg5IXkZcEoh")
            val obj = JSONObject()
            try {
                obj.put("description", "Test Payment")
                obj.put("theme.color", "")
                obj.put("currency", "INR")
                obj.put("amount", pPrice)
                obj.put("prefill.contact", sharedPreferences.getString("mob", ""))
                obj.put("prefill.email", "jchirag2000@gmail.com")
                checkout.open(this, obj)
            }
            catch (e: JSONException) {
                e.printStackTrace()
            }
        }
    }
}