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
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged

class PseScreen : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)

        setContentView(R.layout.pse_layout)

        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.title = "";

        val calculateButton = findViewById<Button>(R.id.pseCalculateBttn);
        val sendButton = findViewById<Button>(R.id.pseSendBttn);
        val inputTime = findViewById<EditText>(R.id.pseTimeValue);


        inputTime.addTextChangedListener();
        inputTime.doOnTextChanged { _, _, _, _ -> updateTime()  }
        calculateButton.setOnClickListener(this);
        sendButton.setOnClickListener(this);

    }

    override fun onClick(p0: View?) {
        if (p0?.id == R.id.pseCalculateBttn || p0?.id == R.id.pseSendBttn) {
            val value =tssCalculator();
            val textView = findViewById<TextView>(R.id.pseResult);
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
            if (p0.id == R.id.pseSendBttn){
                startActivity(availabilityScreen)
            }
        }
    }

    fun updateTime(){

            val convertedTime = findViewById<TextView>(R.id.pseTimeConverted)
            val insertedTime = findViewById<EditText>(R.id.pseTimeValue)

            if (insertedTime.text.toString().toInt() < 1){
                convertedTime.text = getString(R.string.timeZero)
                return
            }
            val hours = insertedTime.text.toString().toInt()/60
            val minutes = insertedTime.text.toString().toInt()%60

            convertedTime.text = getString(R.string.timeformat, hours, minutes)

    }

    fun tssCalculator(): Double {
        var timeValue = 0.0
        var pseValue = 0


        if (findViewById<EditText>(R.id.pseTimeValue).text.isNotEmpty()) {
            timeValue = findViewById<EditText>(R.id.pseTimeValue).text.toString().toDouble();
        }
        if (findViewById<EditText>(R.id.pseValue).text.isNotEmpty()) {
            pseValue = findViewById<EditText>(R.id.pseValue).text.toString().toInt();
        }
        if (timeValue == 0.0 || pseValue == -1){
            return -1.0
        }

        var tssValue = tssByPseCalculator(pseValue, timeValue)
        if (tssValue == -1.0){
            return -1.0
        }


        val firstParameter = tssValue/timeValue;
        var secondParameter= iF(timeValue)
        return secondParameter*firstParameter
    }

    private fun tssByPseCalculator(pseValue: Int, timeValue: Double): Double {

        when(pseValue){

            1 -> return 20*timeValue/60
            2 -> return 30*timeValue/60
            3 -> return 40*timeValue/60
            4 -> return 50*timeValue/60
            5 -> return 60*timeValue/60
            6 -> return 70*timeValue/60
            7 -> return 80*timeValue/60
            8 -> return 100*timeValue/60
            9 -> return 120*timeValue/60
            10 -> return 140*timeValue/60
        }
        return -1.0
    }

    fun iF(value: Double): Double {
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