package com.example.projectgreencare

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject

class Login : AppCompatActivity() {
    lateinit var _rectangle_email: EditText
    lateinit var _rectangle_pw: EditText
    lateinit var _rectangle_login: ImageButton
    lateinit var back: ImageButton

    private val urlLogin: String = "https://nazara-nano.my.id/dbila/login.php"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        _rectangle_email = findViewById(R.id._rectangle_email)
        _rectangle_pw = findViewById(R.id._rectangle_pw)
        _rectangle_login = findViewById(R.id._rectangle_login)
        back = findViewById(R.id.back)

        back.setOnClickListener {
            val nextScreen = Intent(this@Login, MainActivity::class.java)
            startActivity(nextScreen)
        }

        _rectangle_login.setOnClickListener(View.OnClickListener {
            val password: String = _rectangle_pw.text.toString()
            val email: String = _rectangle_email.text.toString()

            if (!(password.isEmpty() || email.isEmpty())) {
                val stringRequest: StringRequest = object : StringRequest(
                    Method.POST,
                    urlLogin,
                    object : com.android.volley.Response.Listener<String?> {
                        override fun onResponse(response: String?) {
                            try {
                                val jsonObject = JSONObject(response)
                                if (jsonObject.has("error")) {

                                    Toast.makeText(
                                        applicationContext,
                                        "Email atau password salah",
                                        Toast.LENGTH_SHORT
                                    ).show()

                                } else {
                                    val id = jsonObject.getJSONObject("payload").getString("id")
                                    val sharedPref: SharedPreferences =
                                        this@Login.getSharedPreferences(
                                            global.Pref_Name,
                                            MODE_PRIVATE
                                        )
                                    val editor = sharedPref.edit()
                                    editor.putString("id", id.toString())
                                    editor.putBoolean("login_session", true)
                                    editor.apply()
                                    Toast.makeText(
                                        applicationContext,
                                        "Selamat datang di HealthTips",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    startActivity(
                                        Intent(
                                            applicationContext,
                                            MainActivity::class.java
                                        )
                                    )
                                }
                            } catch (e: JSONException) {
                                e.printStackTrace()
                            }
                        }
                    },
                    object : com.android.volley.Response.ErrorListener {
                        override fun onErrorResponse(error: VolleyError) {
                            Toast.makeText(applicationContext, error.toString(), Toast.LENGTH_SHORT)
                                .show()
                        }
                    }) {
                    @Throws(AuthFailureError::class)
                    override fun getParams(): HashMap<String, String>? {
                        val params = HashMap<String, String>()
                        params["post_password"] = password
                        params["post_email"] = email
                        return params
                    }
                }
                val requestQueue = Volley.newRequestQueue(
                    applicationContext
                )
                requestQueue.add(stringRequest)
            } else {
                Toast.makeText(applicationContext, "Ada Data Yang Masih Kosong", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }
}