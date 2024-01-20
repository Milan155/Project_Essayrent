package com.example.essayrent

import com.example.essayrent.Models.Cat_model
import com.example.essayrent.Models.Dashboardmodel
import com.example.essayrent.Models.On_house_model
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

interface ApiInterface
{
    @FormUrlEncoded
    @POST("insertadd.php")
    fun insertdata(
        @Field("First_name")first_name:String,
        @Field("Last_name")last_name:String,
        @Field("BOD")bod:String,
        @Field("Email")email:String,
        @Field("Phone")phone:String,
        @Field("Password")password:String
    ): Call<Void>

    @FormUrlEncoded
    @POST("login.php")
    fun logindata
                (
        @Field("Phone")phone: String,
        @Field("Password") password:String,
    ) : Call<Void>

    @GET("C_View.php")
    fun categoryviewdata(): Call<List<Dashboardmodel>>


    @GET("Rental_category_images.php")
    fun categoryimageviewdata() :Call<List<Cat_model>>

    @GET("house_rentview.php")
    fun gethouserent() :Call<List<On_house_model>>

    @FormUrlEncoded
    @POST("Rental_(c)-details.php")
    fun gethouserentc( @Field("data") data:Int): Call<List<On_house_model>>

    @GET("C_view3.php")
    fun categoryviewdata3(): Call<List<Cat_model>>

    @GET("C_view4.php")
    fun getpropertyview() :Call<List<Cat_model>>


//    interface UploadService
//    {
//        @Multipart
//        @POST("uploaddata.php")
//        suspend fun uploadImage(
//            @Query("id") id:Int,
//            @Part image: MultipartBody.Part,
//            @Part("emp_name") emp_name: RequestBody?,
//            @Part("emp_mobile") emp_mobile: RequestBody?,
//            @Part("emp_email") emp_email: RequestBody?,
//            @Part("emp_des") emp_des: RequestBody?,
//        ): ResponseBody
//
//    }

}

