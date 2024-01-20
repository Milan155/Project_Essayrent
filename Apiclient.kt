package com.example.essayrent

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Apiclient
{
    companion object
    {
        lateinit var retrofit: Retrofit
        var BASE_URl="https://unaffecting-firearm.000webhostapp.com/Essayrental-Project/"

        fun getretrofit(): Retrofit
        {

            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit
        }
    }
}