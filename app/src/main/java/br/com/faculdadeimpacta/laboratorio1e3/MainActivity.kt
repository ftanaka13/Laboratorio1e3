package br.com.faculdadeimpacta.laboratorio1e3

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.com.faculdadeimpacta.laboratorio1e3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.constraintLayout) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.buttonCalcular.setOnClickListener {
            val preco = binding.editTextPreco.text.toString().toDoubleOrNull()
            if (preco == null)
                binding.editTextPreco.error = getString(R.string.errorPrice)

            val idade = binding.editTextIdade.text.toString().toIntOrNull()
            if (idade == null)
                binding.editTextIdade.error = getString(R.string.error_age)

            if (preco == null || idade == null) return@setOnClickListener

            fun calcularQuantidadeDesconto(idade:Int): Int = when {
                idade > 50 -> 30
                idade >= 25 -> 15
                else -> 5
            }
            val quantidadeDesconto = calcularQuantidadeDesconto(idade)
            binding.textViewDesconto.text = getString(R.string.discount, quantidadeDesconto.toString())

            fun aplicarDesconto(preco: Double, desconto: Int): Double =
                preco * (1.0 - desconto/100.0)
            val valorFinal = aplicarDesconto(preco, quantidadeDesconto)
            binding.textViewValorFinal.text = getString(R.string.finalValue, valorFinal.toString())
        }
    }
}