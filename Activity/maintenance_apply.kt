package com.example.essayrent.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.essayrent.Models.UploadService
import com.example.essayrent.R
import com.example.essayrent.databinding.ActivityMaintenanceApplyBinding
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.io.FileOutputStream
import java.util.concurrent.Executors


class maintenance_apply : AppCompatActivity() {

    lateinit var binding: ActivityMaintenanceApplyBinding
    lateinit var image: ImageView
    lateinit var btnsubmit: Button
    lateinit var imageuri: Uri
    lateinit var edt1: EditText
    lateinit var edt2: EditText
    lateinit var edt3: EditText
    lateinit var edt4:EditText

    private val contract = registerForActivityResult(ActivityResultContracts.GetContent())
    {
        imageuri = it!!
        image.setImageURI(it)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMaintenanceApplyBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        edt1 = findViewById(R.id.name)
        edt2 = findViewById(R.id.mobile)
        edt3 = findViewById(R.id.email)
        edt4 = findViewById(R.id.desc)

        image = findViewById(R.id.img)
        image.setOnClickListener {
            contract.launch("image/*")
        }
        btnsubmit = findViewById(R.id.register)
        btnsubmit.setOnClickListener {
            upload()
            Toast.makeText(applicationContext, "Please wait for 10-second", Toast.LENGTH_SHORT).show()
            Handler().postDelayed(Runnable {
                var i=Intent(applicationContext, maintenance_req::class.java)
                startActivity(i)
            },10000)
        }
    }

    private fun upload()
    {
        val filesDir = applicationContext.filesDir
        val file = File(filesDir,"image.png")
        val inputstream = contentResolver.openInputStream(imageuri)
        val outputstream = FileOutputStream(file)
        inputstream!!.copyTo(outputstream)

        val requestBody = file.asRequestBody("image/*".toMediaTypeOrNull())
        val image = MultipartBody.Part.createFormData("image",file.name,requestBody)
        val emp_name: RequestBody = RequestBody.Companion.create(MultipartBody.FORM,edt1.text.toString())
        val emp_mobile:RequestBody = RequestBody.Companion.create(MultipartBody.FORM,edt2.text.toString())
        val emp_email:RequestBody = RequestBody.Companion.create(MultipartBody.FORM,edt3.text.toString())
        val emp_des:RequestBody = RequestBody.Companion.create(MultipartBody.FORM,edt4.text.toString())

        val retrofit = Retrofit.Builder()
            .baseUrl("https://unaffecting-firearm.000webhostapp.com/Essayrental-Project/")
            .addConverterFactory(GsonConverterFactory.create())
            .callbackExecutor(Executors.newSingleThreadExecutor())
            .build()
            .create(UploadService::class.java)

        CoroutineScope(Dispatchers.IO).launch {
                val response=retrofit.uploadImage(image,emp_name,emp_mobile,emp_email,emp_des)
                Log.d("Milan12345",response.toString())
        }
    }

}
