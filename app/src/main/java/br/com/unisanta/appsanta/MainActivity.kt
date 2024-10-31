package br.com.unisanta.appsanta

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.unisanta.appsanta.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth

        binding.btnLogin.setOnClickListener{
            auth.signInWithEmailAndPassword(binding.edtEmail.text.toString(), binding.edtSenha.text.toString())
                .addOnCompleteListener(this){ task ->
                    if(task.isSuccessful){
                        Toast.makeText(this,"Sucesso", Toast.LENGTH_LONG).show()

                    }
                    else{
                        Toast.makeText(this,"Falhou", Toast.LENGTH_LONG).show()
                    }
                }
        }
        binding.btnCadastro.setOnClickListener{
            val intent = Intent(
                this,
                Cadastro::class.java
            )
            startActivity(intent)
        }
        binding.button2.setOnClickListener{
            val email = binding.edtEmail.text.toString()

            if (email.isNotEmpty()) {
                auth.sendPasswordResetEmail(email)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "E-mail de redefinição de senha enviado!", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(this, "Erro: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
            } else {
                Toast.makeText(this, "Por favor, insira um e-mail.", Toast.LENGTH_SHORT).show()
            }
        }

    }
}