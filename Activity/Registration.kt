package com.example.essayrent.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.essayrent.ApiInterface
import com.example.essayrent.Apiclient
import com.example.essayrent.R
import com.example.essayrent.databinding.ActivityRegistrationBinding
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.TimeUnit

class Registration : AppCompatActivity() {

    lateinit var binding: ActivityRegistrationBinding
    lateinit var sharedPreferences: SharedPreferences
    lateinit var sharedPreferencesuser: SharedPreferences

    lateinit var edtOTP: EditText
    lateinit var verificationid:String
    lateinit var auth: FirebaseAuth
    lateinit var mcallback: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    lateinit var apiInterface: ApiInterface


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        auth = FirebaseAuth.getInstance()
        edtOTP=findViewById(R.id.edtOTP)

        apiInterface= Apiclient.getretrofit().create(ApiInterface::class.java)

        sharedPreferences = getSharedPreferences("SESSION", Context.MODE_PRIVATE)
        sharedPreferencesuser= getSharedPreferences("USER_SESSION", Context.MODE_PRIVATE)

        if (sharedPreferences.getBoolean("SESSION", false) && !sharedPreferences.getString("phone", "")!!.isEmpty())
        {
            startActivity(Intent(applicationContext, Login_Code::class.java))
        }
        if (sharedPreferencesuser.getBoolean("USER_SESSION", false) && !sharedPreferencesuser.getString("phone", "")!!.isEmpty())
        {
            startActivity(Intent(applicationContext, Login_Code::class.java))
        }

        binding.userLoginTxt.setOnClickListener {
            startActivity(Intent(this, Login_Code::class.java))
        }

        binding.imgVerify.setOnClickListener {
            if (TextUtils.isEmpty(binding.edtPhone.text.toString())) { Toast.makeText(this@Registration,
                    "Please enter a valid phone number.", Toast.LENGTH_SHORT).show()
            } else {

                var mob = binding.edtPhone.text.toString()
                sendverificationcode(mob)
                edtOTP.visibility = View.VISIBLE
            }
        }

            binding.btnRegister1.setOnClickListener {

                if (TextUtils.isEmpty(edtOTP.text.toString())) {

                    Toast.makeText(this@Registration, "Please enter a valid OTP.", Toast.LENGTH_SHORT).show()
                }
                else { val otp: String = edtOTP.text.toString()
                    verifycode(otp)
                }
            }

            mcallback = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                    var code = p0.smsCode
                    if (code != null) {
                        edtOTP.setText(code)
                    } else {
                        Toast.makeText(applicationContext, "error", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onVerificationFailed(p0: FirebaseException) {
                    p0.printStackTrace()
                    Toast.makeText(applicationContext, "failed" + p0.message, Toast.LENGTH_SHORT)
                        .show()

                }

                override fun onCodeSent(p0: String, p1: PhoneAuthProvider.ForceResendingToken) {
                    verificationid = p0
                }
            }
        }


        override fun onBackPressed() {
            finishAffinity()
            super.onBackPressed()
        }

        private fun verifycode(otp: String) {
            val credential = PhoneAuthProvider.getCredential(verificationid,otp)
            signinwithcredential(credential)
        }

    private fun signinwithcredential(credential: PhoneAuthCredential) {

       // var m = Model()
        auth.signInWithCredential(credential)
            .addOnCompleteListener {
                if (it.isSuccessful)
                {
                    var firstname=binding.edtFName.text.toString()
                    var lastname=binding.edtLName.text.toString()
                    var dob=binding.edtDob.text.toString()
                    var email=binding.edtEmail.text.toString()
                    var phone=binding.edtPhone.text.toString()
                    var password=binding.edtPassword.text.toString()


                    if (firstname.length == 0 && lastname.length==0 && dob.length == 0 && email.length==0 &&
                        phone.length == 0 && password.length == 0)
                    {
                        binding.edtFName.setError("Please enter first name")
                        binding.edtLName.setError("Please enter last name")
                        binding.edtDob.setError("Please enter email")
                        binding.edtEmail.setError("Please enter Date of birth")
                        binding.edtPhone.setError("Please enter phone number")
                        binding.edtPassword.setError("Please enter password")
                    }
                    else if (firstname.length==0) {
                        binding.edtFName.setError("Enter first name")
                    }
                    else if (lastname.length==0) {
                        binding.edtLName.setError("Enter last name")
                    }
                    else if (dob.length == 0) {
                        binding.edtDob.setError("Enter error")
                    }
                    else if (email.length == 0) {
                        binding.edtEmail.setError("Enter Email")
                    }else if (phone.length == 0) {
                        binding.edtPhone.setError("Enter Email")
                    }
                    else if (password.length == 0) {
                        binding.edtPassword.setError("Enter Email")
                    }
                    var call:Call<Void> = apiInterface.insertdata(firstname,lastname,dob,email,phone,password)
                    call.enqueue(object :Callback<Void>{

                        override fun onResponse(call: Call<Void>, response: Response<Void>) {

                            binding.edtFName.setText("")
                            binding.edtLName.setText("")
                            binding.edtDob.setText("")
                            binding.edtEmail.setText("")
                            binding.edtPhone.setText("")
                            binding.edtPassword.setText("")

                            var editor: SharedPreferences.Editor = sharedPreferencesuser.edit()
                            editor.putBoolean("SESSION", true)
                            editor.putString("phone", phone)
                            editor.putString("password", password)
                            editor.commit()
                            Toast.makeText(applicationContext, "Data Inserted", Toast.LENGTH_SHORT).show()

// new user registr profile view
//                            val myEdit = sharedPreferences.edit()
//
//                            //Toast.makeText(applicationContext,""+m.id,Toast.LENGTH_LONG).show()
//
//                            myEdit.putInt("id", m.id)
//                            myEdit.putString("name",firstname)
//                            myEdit.putString("lastname",lastname)
//                            myEdit.putString("dob",dob)
//                            myEdit.putString("email",email)
//                            myEdit.putString("phone",phone)
//                            myEdit.putString("password",password)
//                            myEdit.apply()
//
//
//                            m.First_name = firstname
//                            m.Last_name=lastname
//                            m.BOD=dob
//                            m.Email=email
//                            m.Phone=phone
//                            m.Password=password

                            // login user profile view
                            // val myEdit = sharedPreferencesUser.edit()etl

                        }
                        override fun onFailure(call: Call<Void>, t: Throwable) {
                            Toast.makeText(applicationContext, "Data Fail to Insert", Toast.LENGTH_SHORT).show()
                        }
                    })
                } else {
                    Toast.makeText(applicationContext, "Wrong-OTP", Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener {
                Toast.makeText(applicationContext, "Error", Toast.LENGTH_SHORT).show()
               }
           startActivity(Intent(applicationContext, Login_Code::class.java))
    }

    private fun sendverificationcode(mob: String) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(mob,60, TimeUnit.SECONDS,this,mcallback)
    }
}



