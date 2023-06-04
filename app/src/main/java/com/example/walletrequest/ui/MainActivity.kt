package com.example.walletrequest.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.walletrequest.ConfRetrofit
import com.example.walletrequest.R

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val openCard = findViewById<Button>(R.id.btCadastrar)
        openCard.setOnClickListener {
            val intentCard = Intent(this, CadastroActivity::class.java)
            startActivity(intentCard)
        }



    }
}