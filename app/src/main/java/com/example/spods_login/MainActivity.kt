package com.example.spods_login

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.widget.ProgressBar
import androidx.core.view.isVisible
import com.example.spods_login.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()
        window.statusBarColor = Color.parseColor("#FFFFFF")

        binding.btnEntrar.setOnClickListener{

            val email=binding.editEmail.text.toString()
            val senha=binding.editSenha.text.toString()

            when{
                email.isEmpty() -> {
                    binding.editEmail.error = "Preencha o Email!"
                }

                senha.isEmpty() ->{
                    binding.editSenha.error = "Preencha a Senha!"
                }

                !email.contains("@gmail.com") -> {
                    val snackbar = Snackbar.make(it,"Email inválido", Snackbar.LENGTH_SHORT)
                    snackbar.show()
                }

                senha.length <=5 -> {
                    val snackbar = Snackbar.make(it,"A Senha precisa ter ao menos 6 caracteries", Snackbar.LENGTH_SHORT)
                    snackbar.show()
                }

                else -> {
                    //login de usuário
                    auth.signInWithEmailAndPassword(email,senha).addOnCompleteListener{
                        autenticacao ->
                        if(autenticacao.isSuccessful){
                            navegarTelaInicial()
                        }
                    }
                }
            }
        }
    }
    private fun login(view:View){

        val progressbar = binding.progressBar
        progressbar.visibility = View.VISIBLE

        binding.btnEntrar.isEnabled = false
        binding.btnEntrar.setTextColor(Color.parseColor("#FFFFFF"))

        Handler(Looper.getMainLooper()).postDelayed({
            navegarTelaInicial()
            val snackbar = Snackbar.make(view,"login efetuado com sucesso!",Snackbar.LENGTH_SHORT)
        },3000)
    }

    private fun navegarTelaInicial(){
        val intent = Intent(this,TelaInicial::class.java)
        startActivity(intent)
        finish()
    }
}

