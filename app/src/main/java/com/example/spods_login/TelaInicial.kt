package com.example.spods_login

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class TelaInicial : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_inicial)

        supportActionBar!!.hide()
        window.statusBarColor= Color.parseColor("#FCE06D1F")
    }
}