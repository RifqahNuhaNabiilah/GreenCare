package com.example.projectgreencare

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject


class Login : AppCompatActivity() {

    private lateinit var _rectangle_email: EditText
    private lateinit var _rectangle_pw: EditText
    private lateinit var _rectangle_login: ImageButton
    private var urlSignIn: String = "https://nazara-nano.my.id/dbila/login.php"

    constructor(parcel: Parcel) : this() {
        urlSignIn = parcel.readString().toString()
    }

    constructor()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        _rectangle_email = findViewById(R.id._rectangle_email)
        _rectangle_pw = findViewById(R.id._rectangle_pw)
        _rectangle_login = findViewById(R.id._rectangle_login)

        _rectangle_login.setOnClickListener {
            val password: String = _rectangle_pw.text.toString().trim()
            val username: String = _rectangle_email.text.toString().trim()

            if (!(password.isEmpty() || username.isEmpty())) {
                val stringRequest: StringRequest = object : StringRequest(
                    Request.Method.POST,
                    urlSignIn,
                    object : com.android.volley.Response.Listener<String?> {
                        override fun onResponse(response: String?) {
                            if (response != null) {
                                val jsonObject = JSONObject(response)
                                Log.d("testingg", jsonObject.toString())
                                val success = jsonObject.getBoolean("success")
                                val message = jsonObject.getString("message")
                                if (success) {
                                    // Jika login berhasil
                                    val sharedPref: SharedPreferences = this@Login.getSharedPreferences(global.Pref_Name, MODE_PRIVATE)
                                    val editor = sharedPref.edit()
                                    editor.putString("username", username)
                                    editor.putString("password", password)
                                    //editor.putBoolean("login_session", true)
                                    editor.apply()

                                    Log.d("SignInResponse", "Login berhasil. Selamat datang, $username.")
                                    Toast.makeText(applicationContext, "Selamat datang di HealthTips", Toast.LENGTH_SHORT).show()
                                    startActivity(Intent(applicationContext, Menu::class.java))
                                } else {
                                    // Jika ada pesan kesalahan dari server
                                    Log.e("SignInResponse", "Login gagal. Pesan dari server: $message")
                                    Toast.makeText(applicationContext, "Gagal login: $message", Toast.LENGTH_SHORT).show()
                                }

                            }else{
                                Toast.makeText(applicationContext, "Terjadi kesalahan saat login.", Toast.LENGTH_SHORT).show()
                            }
                        }
                    },
                    /* errorListener = */ object : com.android.volley.Response.ErrorListener {
                        override fun onErrorResponse(error: VolleyError) {

                            try {
                                val errorObj = JSONObject(String(error.networkResponse.data))
                                val errorMessage = errorObj.getString("message")
                                Toast.makeText(applicationContext, errorMessage, Toast.LENGTH_SHORT).show()
                            } catch (e: Exception) {
                                Toast.makeText(applicationContext, "Terjadi kesalahan saat login.", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }) {
                    override fun getBodyContentType(): String {
                        return "application/json"
                    }


                    @Throws(AuthFailureError::class)
                    override fun getBody(): ByteArray {
                        val params = HashMap<String, String>()
                        params["username"] = username
                        params["password"] = password
                        return JSONObject(params as Map<*, *>?).toString().toByteArray(Charsets.UTF_8)
                    }
                    override fun getHeaders(): Map<String, String> {
                        val headers = HashMap<String, String>()
                        // Tidak perlu menetapkan Content-Type secara eksplisit, karena sudah diatur di getBodyContentType
                        return headers
                    }
                }

                Volley.newRequestQueue(applicationContext).add(stringRequest)
            } else {
                Toast.makeText(applicationContext, "Kolom belum terisi", Toast.LENGTH_SHORT).show()
            }
        }

        val back: ImageButton = findViewById(R.id.back)
        back.setOnClickListener {
            val intent: Intent = Intent(this@Login, MainActivity::class.java)
            startActivity(intent)
        }
    }

//    fun writeToParcel(parcel: Parcel, flags: Int) {
//        parcel.writeString(urlSignIn)
//    }
//
//    fun describeContents(): Int {
//        return 0
//    }
//
//    companion object CREATOR : Parcelable.Creator<Login> {
//        override fun createFromParcel(parcel: Parcel): Login {
//            return Login(parcel)
//        }
//
//        override fun newArray(size: Int): Array<Login?> {
//            return arrayOfNulls(size)
//        }
//    }
}