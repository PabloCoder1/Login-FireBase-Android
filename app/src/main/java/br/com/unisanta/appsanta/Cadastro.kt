package br.com.unisanta.appsanta

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.com.unisanta.appsanta.databinding.ActivityCadastroBinding
import br.com.unisanta.appsanta.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Cadastro : AppCompatActivity() {
    private lateinit var binding: ActivityCadastroBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth

        binding.btnCriar.setOnClickListener{
            auth.createUserWithEmailAndPassword(binding.edtCriarEmail.text.toString(), binding.edtCriarSenha.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show()
                        val intent = Intent(
                            this,
                            MainActivity::class.java
                        )
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, "Falha ao cadastrar: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
        }


    }
}