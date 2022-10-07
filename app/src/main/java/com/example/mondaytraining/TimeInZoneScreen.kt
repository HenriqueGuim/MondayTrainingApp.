package com.example.mondaytraining

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate

class TimeInZoneScreen : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time_in_zone_layout);

        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.title = "";

        val calculateButton = findViewById<Button>(R.id.time_zone_buttn);
        val sendButton = findViewById<Button>(R.id.timeZoneSendBttn);

        calculateButton.setOnClickListener(this);
        sendButton.setOnClickListener(this);

    }

    override fun onClick(p0: View?) {

        if (p0?.id == R.id.time_zone_buttn || p0?.id == R.id.timeZoneSendBttn) {

            val value = tssCalculator()
            val textView = findViewById<TextView>(R.id.timeInZoneResult);
            hideKeyboard();

            if (value == -1.0){
                textView.setText("Insere valores válidos")
                return;
            }

            if(value <0.85){
                textView.setText("Bloco Z3")
            } else  if(value >1.1){
                textView.setText("Recuperação ativa")
            } else {
                textView.setText("Bloco Z2 ou Descanso")
            }

            val availabilityScreen = Intent(this, AvailabilityScreen::class.java)
            val bundle = Bundle()
            bundle.putString("tssValue", textView.text.toString())
            availabilityScreen.putExtras(bundle)
            if (p0.id == R.id.timeZoneSendBttn){
                startActivity(availabilityScreen)
            }
        }
    }

    private fun tssCalculator(): Double {
        var zone1time = 0
        var zone2time = 0
        var zone3time = 0
        var zone4time = 0
        var zone5time = 0

        if (findViewById<EditText>(R.id.zone1Value).text.isNotEmpty()){
            zone1time = Integer.parseInt(findViewById<EditText>(R.id.zone1Value).text.toString())
        }
        if (findViewById<EditText>(R.id.zone2Value).text.isNotEmpty()){
            zone2time = Integer.parseInt(findViewById<EditText>(R.id.zone2Value).text.toString())
        }
        if (findViewById<EditText>(R.id.zone3Value).text.isNotEmpty()){
            zone3time = Integer.parseInt(findViewById<EditText>(R.id.zone3Value).text.toString())
        }
        if (findViewById<EditText>(R.id.zone4Value).text.isNotEmpty()){
            zone4time = Integer.parseInt(findViewById<EditText>(R.id.zone4Value).text.toString())
        }
        if (findViewById<EditText>(R.id.zone5Value).text.isNotEmpty()){
            zone5time = Integer.parseInt(findViewById<EditText>(R.id.zone5Value).text.toString())
        }

        val totalTime = zone1time + zone2time + zone3time + zone4time + zone5time

        if(totalTime == 0){
            return -1.0
        }


        val zone1Tss = zone1time.toDouble()/60*30
        val zone2Tss = zone2time.toDouble()/60*55
        val zone3Tss = zone3time.toDouble()/60*70
        val zone4Tss = zone4time.toDouble()/60*80
        val zone5Tss = zone5time.toDouble()/60*100
        val totalTss = zone1Tss + zone2Tss + zone3Tss + zone4Tss + zone5Tss

        val firstParameter = totalTss/totalTime;
        var secondParameter= iF(totalTime)
        return secondParameter*firstParameter
    }

    fun iF(value:Int): Double {
        if (value >= 720) {
            return 1.55;
        }
        if (value >= 660) {
            return 1.5;
        }
        if (value >= 600) {
            return 1.45;
        }
        if (value >= 540) {
            return 1.4;
        }
        if (value >= 480) {
            return 1.35;
        }
        if (value >= 420) {
            return 1.3;
        }
        if (value >= 360) {
            return 1.25;
        }
        if (value >= 300) {
            return 1.2;
        }
        if (value >= 240) {
            return 1.15;
        }
        if (value >= 180) {
            return 1.1;
        }
        if (value >= 120) {
            return 1.05;
        }
        if (value >= 60) {
            return 1.0;
        }

        return 0.9;
    }
    fun hideKeyboard(){
        val view = this.currentFocus;
        val hideMe = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager;
        hideMe.hideSoftInputFromWindow(view?.windowToken, 0)

        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
    }

    fun sendMessage(message: String){
        val packageManager : PackageManager = packageManager
        val intent : Intent = Intent(Intent.ACTION_SEND)

        val messageToSent:String = "Bom dia Sergio, \n o treino de segunda é: $message";

        intent.type ="text/plain"
        intent.setPackage("com.whatsapp")
        intent.putExtra(Intent.EXTRA_TEXT, message);

        if (intent
                .resolveActivity(
                    getPackageManager())
            == null) {
            Toast.makeText(
                this,
                "Please install whatsapp first.",
                Toast.LENGTH_SHORT)
                .show();
            return;
        }

        startActivity(intent)
    }
}
