package com.s.ajrami.zoo.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.s.ajrami.zoo.R
import kotlinx.android.synthetic.main.activity_splash_screen.*

class Splash_screen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)


        Handler().postDelayed({

            val i = Intent(splashScreen@ this, LogIn::class.java)
            startActivity(i)
            finish()
        }, 4000)

        YoYo.with(Techniques.RotateIn).repeat(5).duration(5000).playOn(splash)
    }
}

