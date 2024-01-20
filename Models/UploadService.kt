package com.example.essayrent.Models

import okhttp3.Call
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

interface UploadService {
    @Multipart
    @POST("uploaddata.php")
    suspend fun uploadImage(
        @Part image: MultipartBody.Part,
        @Part("emp_name") emp_name: RequestBody?,
        @Part("emp_mobile") emp_mobile: RequestBody?,
        @Part("emp_email") emp_email: RequestBody?,
        @Part("emp_des") emp_des: RequestBody?,
    ): ResponseBody

    @GET("M_history.php")
    fun get_data() :retrofit2.Call<List<m_history_model>>
}
