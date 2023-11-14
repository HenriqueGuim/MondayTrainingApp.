package com.example.mondaytraining

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val TAG = "MainActivity"
    private var doubleBackToExitPressedOnce = false

    override fun onCreate(savedInstanceState: Bundle?) {

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val clickTssScreen = findViewById<Button>(R.id.tssButton);
        val clickHrTssScreen = findViewById<Button>(R.id.hr_tss_button);
        val clickPse = findViewById<Button>(R.id.PseButton)

        clickTssScreen.setOnClickListener(this);
        clickHrTssScreen.setOnClickListener(this);
        clickPse.setOnClickListener(this);

        supportActionBar?.title = "Treino de Segunda";
    }

    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        }

        this.doubleBackToExitPressedOnce = true
        Toast.makeText(this, "Pressiona VOLTAR novamente para sair", Toast.LENGTH_SHORT).show()

        Handler(Looper.getMainLooper()).postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 2000)
    }

    override fun onClick(view: View?) {
        val tss = Intent(this, TssScreen::class.java)
        val hrTss = Intent(this, HrTssScreen::class.java)
        val timeZone = Intent(this, TimeInZoneScreen::class.java)
        val pse = Intent(this, PseScreen::class.java)

        //val availabilityScreen = Intent(this,AvailabilityScreen::class.java)

        if (view != null) {
            when(view.id){
                R.id.tssButton -> startActivity(tss)
                R.id.hr_tss_button -> startActivity(hrTss)
                R.id.PseButton -> startActivity(pse)
            }
        }
    }
}