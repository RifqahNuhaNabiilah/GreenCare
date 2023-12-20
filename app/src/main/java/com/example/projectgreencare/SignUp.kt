package com.example.projectgreencare

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.Request.Method
import com.android.volley.Response.ErrorListener
import com.android.volley.Response.Listener
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class SignUp : AppCompatActivity() {

    lateinit var rectangle_full_name: EditText
    lateinit var rectangle_Email: EditText
    lateinit var rectangle_psswrd: EditText
    lateinit var confirm_button: EditText
    lateinit var rectangle_signUp: ImageButton
    lateinit var back :ImageButton

    var urlSignUp: String = "https://nazara-nano.my.id/dbila/signin.php"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)



        rectangle_full_name = findViewById(R.id.rectangle_full_name)
        rectangle_Email = findViewById(R.id.rectangle_Email)
        rectangle_psswrd = findViewById(R.id.rectangle_psswrd)
        confirm_button = findViewById(R.id.confirm_button)
        rectangle_signUp = findViewById(R.id.rectangle_signUp)
        back = findViewById(R.id.back)

        back.setOnClickListener{
            val nextScreen = Intent(this@SignUp, MainActivity::class.java)
            startActivity(nextScreen)
        }

        rectangle_signUp.setOnClickListener {
            val username: String = rectangle_full_name.text.toString()
            val password: String = rectangle_psswrd.text.toString()
            val confirmPassword: String = confirm_button.text.toString()
            val email: String = rectangle_Email.text.toString()

            if (!(username.isEmpty() || password.isEmpty() || email.isEmpty())) {
                if (password == confirmPassword) {
                    val stringRequest: StringRequest = object : StringRequest(
                        Method.POST,
                        urlSignUp,
                        object : Listener<String?> {
                            override fun onResponse(response: String?) {
                                Toast.makeText(applicationContext, "Registrasi sukses, silahkan login kembali", Toast.LENGTH_SHORT).show()
                                startActivity(Intent(applicationContext, MainActivity::class.java))
                            }
                        },
                        object : ErrorListener {
                            override fun onErrorResponse(error: VolleyError) {
                                Toast.makeText(applicationContext, error.toString(), Toast.LENGTH_SHORT).show()
                            }
                        }) {
                        @Throws(AuthFailureError::class)
                        override fun getParams(): Map<String, String> {
                            val params = HashMap<String, String>()
                            params["post_name"] = username
                            params["post_password"] = password
                            params["post_email"] = email
                            return params
                        }
                    }

                    val requestQueue = Volley.newRequestQueue(applicationContext)
                    requestQueue.add(stringRequest)
                } else {
                    Toast.makeText(applicationContext, "Password dan Confirm Password tidak sama", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(applicationContext, "Ada Data Yang Masih Kosong", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
