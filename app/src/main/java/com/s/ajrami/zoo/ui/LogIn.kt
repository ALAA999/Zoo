package com.s.ajrami.zoo.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.s.ajrami.zoo.R
import kotlinx.android.synthetic.main.activity_log_in.*

class LogIn : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        btn_login.setOnClickListener {
            var name: String = login_name.text.toString()
            var pass: String = login_password.text.toString()

            if (name.equals("admin")&&pass.equals("admin")) {
                var i = Intent(this, MainActivity::class.java)
                startActivity(i)            }

            else
                Toast.makeText(this, "Name or password wrong", Toast.LENGTH_SHORT).show()
            }
        }
}



