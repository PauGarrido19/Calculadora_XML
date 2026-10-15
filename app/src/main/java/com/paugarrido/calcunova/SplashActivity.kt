package com.paugarrido.calcunova

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val modoGuardado = getSharedPreferences("ajustes", MODE_PRIVATE)
            .getInt("modo", AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        AppCompatDelegate.setDefaultNightMode(modoGuardado)

        enableEdgeToEdge()
        setContentView(R.layout.activity_splash)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val botonEntrar = findViewById<Button>(R.id.btnEntrar)
        botonEntrar.setOnClickListener {
            startActivity(Intent(this, CalculadoraActivity::class.java))
            finish()
        }

        val btnModoNoche = findViewById<Button>(R.id.btnModo)
        btnModoNoche.setOnClickListener {
            // Verificamos cuál es el modo actual de la aplicación
            val modoActual = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK

            if (modoActual == Configuration.UI_MODE_NIGHT_YES) {
                // Si ya está en modo noche, lo cambiamos a modo día
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                btnModoNoche.text = "☀"
            } else {
                // Si está en modo día (o normal), lo cambiamos a modo noche
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                btnModoNoche.text = "🌙"
            }
        }
    }
}