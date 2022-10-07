package com.example.mondaytraining

import android.annotation.SuppressLint
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


class TssScreen : AppCompatActivity(), View.OnClickListener {
    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tss_layout);

        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.title = "";

        val calculateButton = findViewById<Button>(R.id.tssCalculateBttn);
        val sendButton = findViewById<Button>(R.id.tssSendBttn);

        calculateButton.setOnClickListener(this);
        sendButton.setOnClickListener(this);
    }

    override fun onClick(p0: View?) {
        if (p0?.id == R.id.tssCalculateBttn || p0?.id == R.id.tssSendBttn) {
            val value =tssCalculator();
            val textView = findViewById<TextView>(R.id.tssResult);
            hideKeyboard();

            if (value == -1.0){
                textView.setText("Insere valores válidos")

                return;
            }



            if(value <0.85){
                textView.setText("Bloco Z3")
                //sendMessage("Bloco Z3")
                //return;
            } else  if(value >1.1){
                textView.setText("Recuperação ativa")
                //sendMessage("Recuperação ativa")
                //return
            } else {
                textView.setText("Bloco Z2 ou Descanso")
            }



            val availabilityScreen = Intent(this, AvailabilityScreen::class.java)
            val bundle = Bundle()
            bundle.putString("tssValue", textView.text.toString())
            availabilityScreen.putExtras(bundle)
            if (p0.id == R.id.tssSendBttn){
                startActivity(availabilityScreen)
            }
        }
    }

    fun tssCalculator(): Double {
        var timeValue = 0.0
        var tssValue = 0.0

        if (findViewById<EditText>(R.id.timeValue).text.isNotEmpty()) {
            timeValue = findViewById<EditText>(R.id.timeValue).text.toString().toDouble();
        }
        if (findViewById<EditText>(R.id.tssValue).text.isNotEmpty()) {
            tssValue = findViewById<EditText>(R.id.tssValue).text.toString().toDouble();
        }
        if (timeValue == 0.0 || tssValue == 0.0){
            return -1.0
        }


        val firstParameter: Double = tssValue/timeValue;
        var secondParameter= iF(timeValue)
        return secondParameter*firstParameter
    }

    fun iF(value:Double): Double {
        if(value >= 720){
            return 1.55;
        }
        if(value >= 660){
            return 1.5;
        }
        if(value >= 600){
            return 1.45;
        }
        if(value >= 540){
            return 1.4;
        }
        if(value >= 480){
            return 1.35;
        }
        if(value >= 420){
            return 1.3;
        }
        if(value >= 360){
            return 1.25;
        }
        if(value >= 300){
            return 1.2;
        }
        if(value >= 240){
            return 1.15;
        }
        if(value >= 180){
            return 1.1;
        }
        if(value >= 120){
            return 1.05;
        }
        if(value >= 60){
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

    fun isWhatsAppInstalled() : Boolean{

        val packageManager : PackageManager = packageManager
        var isInstalled = true;

        try {
            packageManager.getPackageInfo("com.whatsapp", PackageManager.GET_ACTIVITIES)
            isInstalled = true
        } catch (e: Exception) {

        }

        return isInstalled

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