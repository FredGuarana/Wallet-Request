package com.example.walletrequest.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.MbmsGroupCallSession
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.walletrequest.ConfRetrofit
import com.example.walletrequest.R
import com.example.walletrequest.model.Card
import com.example.walletrequest.service.ServiceCard
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.UUID

import kotlin.reflect.typeOf

class CadastroActivity : AppCompatActivity() {

    private lateinit var configuracao: ConfRetrofit
    private lateinit var servico: ServiceCard

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        configuracao = ConfRetrofit()
        servico = configuracao.service

        val edName = findViewById<EditText>(R.id.nomeTitular)
        val edNumber = findViewById<EditText>(R.id.numCartao)
        val edVencimento = findViewById<EditText>(R.id.etVencimento)
        val edCode = findViewById<EditText>(R.id.etCodSeguranca)
        val btAvancar = findViewById<Button>(R.id.btAvancar)
        btAvancar.setOnClickListener {

            val id: String = UUID.randomUUID().toString()
            val type = "BLACK"
            val name = edName.text
            val number = edNumber.text
            val vencimento = edVencimento.text
            val code = edCode.text

            val newCard = Card(
                id = id,
                name = name.toString(),
                number = number.toString(),
                cvv = code.toString(),
                expirationDate = vencimento.toString(),
                cardType = type
            )

            executarRequest(newCard)
        }

    }

    private fun executarRequest(newCard: Card) {
        servico.createCard(newCard).enqueue(object : Callback<Card> {
            override fun onResponse(call: Call<Card>, response: Response<Card>) {
                Toast.makeText(this@CadastroActivity, "Sucesso! Dados cadastrados com sucesso!", Toast.LENGTH_LONG).show()
            }

            override fun onFailure(call:Call<Card>, t:Throwable){
                Log.e("FATAL",t.message.orEmpty())
                Toast.makeText(this@CadastroActivity, "Erro! Falha de comunicação!", Toast.LENGTH_LONG).show()

            }
        })

    }


}