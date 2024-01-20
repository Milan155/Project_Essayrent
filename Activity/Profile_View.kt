package com.example.essayrent.Activity
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.essayrent.Models.Model
import com.example.essayrent.databinding.ActivityProfileViewBinding
import org.json.JSONException
import org.json.JSONObject

class Profile_View : AppCompatActivity() {

    lateinit var binding: ActivityProfileViewBinding
    lateinit var sharedPreferences: SharedPreferences


    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileViewBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        sharedPreferences=getSharedPreferences("USER_SESSION",Context.MODE_PRIVATE)

        var phone=sharedPreferences.getString("phone", "")
        var pass=sharedPreferences.getString("password", "")

        var stringrequest: StringRequest = object: StringRequest(
            Request.Method.POST,"https://unaffecting-firearm.000webhostapp.com/Essayrental-Project/profile_View.php",{
                    response->
                try
                {
                    var jsonObject = JSONObject(response)
                    var jsonArray = jsonObject.getJSONArray("result")

                    for(i in 0..jsonArray.length())
                    {
                        var jsonObject2 = jsonArray.getJSONObject(i)

                        var id = jsonObject2.getInt("id")
                        var name = jsonObject2.getString("First_name")
                        var lname=jsonObject2.getString("Last_name")
                        var bod=jsonObject2.getString("BOD")
                        var email=jsonObject2.getString("Email")
                        var phone=jsonObject2.getString("Phone")
                        var password = jsonObject2.getString("Password")

                        // this model filed name is same as database filed
                        var m= Model()
                        m.id=id
                        m.First_name=name
                        m.Last_name=lname
                        m.BOD=bod
                        m.Email=email
                        m.Phone=phone
                        m.Password=password

                        //set the text in profile for view data
                        binding.edtFName.setText(name)
                        binding.edtLName.setText(lname)
                        binding.edtDob.setText(bod)
                        binding.edtEmail.setText(email)
                        binding.edtPhone.setText(phone)
                        binding.edtPassword.setText(password)

                        Toast.makeText(applicationContext,"Done",Toast.LENGTH_SHORT ).show()
                    }
                }
                catch(e: JSONException)
                {
                    e.printStackTrace()
                }
            },
            {
                Toast.makeText(applicationContext,"No Internet", Toast.LENGTH_LONG).show()
            })
        {
            //Hashmap is using to store value of string
            override fun getParams(): MutableMap<String, String>?
            {
                var map = HashMap<String,String>()
                map["Phone"]=phone.toString()
                map["Password"]=pass.toString()
                return map
            }
        }

        var queue: RequestQueue = Volley.newRequestQueue(this)
        queue.add(stringrequest)

        }
    }


























//
//        sharedPreferencesUser = getSharedPreferences("SESSION", Context.MODE_PRIVATE)
//
//            val id=sharedPreferencesUser.getInt("id",101)
//            val name = sharedPreferencesUser.getString("First_name", "")
//            val lastname = sharedPreferencesUser.getString("lastname", "")
//            val Dob = sharedPreferencesUser.getString("dob", "")
//            val email = sharedPreferencesUser.getString("email", "")
//            val phone = sharedPreferencesUser.getString("phone", "")
//            val password = sharedPreferencesUser.getString("password", "")
//
//            var m=Model()
//            m.id=id.toInt()
//            m.First_name= name!!
//            m.Last_name= lastname!!
//            m.BOD= Dob!!
//            m.Email= email!!
//            m.Phone= phone!!
//            m.Password= password!!
//
//            binding.edtFName.setText(name)
//            binding.edtLName.setText(lastname)
//            binding.edtDob.setText(Dob)
//            binding.edtEmail.setText(email)
//            binding.edtPhone.setText(phone)
//            binding.edtPassword.setText(password)
//
//        apiInterface = ApiClient.getretrofit()!!.create(ApiInterface::class.java)
//
//        var call = apiInterface.profileviewDetail(phone,password)
//        call.enqueue(object :Callback<Void>{
//            override fun onResponse(call: Call<Void>, response: Response<Void>) {
//
//                Toast.makeText(applicationContext,"" +
//                        "aa  "+response.toString(),Toast.LENGTH_SHORT).show()
//            }
//
//            override fun onFailure(call: Call<Void>, t: Throwable) {
//
//            }
//        })
//    }
//
//}


