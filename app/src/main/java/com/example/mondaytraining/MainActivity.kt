package com.example.mondaytraining

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val clickTssScreen = findViewById<Button>(R.id.tssButton);
        val clickHrTssScreen = findViewById<Button>(R.id.hr_tss_button);
        val clickTimeZone = findViewById<Button>(R.id.timeZoneButton);
        val clickPse = findViewById<Button>(R.id.PseButton)

        clickTssScreen.setOnClickListener(this);
        clickHrTssScreen.setOnClickListener(this);
        clickTimeZone.setOnClickListener(this);
        clickPse.setOnClickListener(this);

        supportActionBar?.title = "Treino de Segunda";
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
                R.id.timeZoneButton -> startActivity(timeZone)
                R.id.PseButton -> startActivity(pse)
            }
        }
    }
}