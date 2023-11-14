package com.example.mondaytraining

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate


class AvailabilityScreen : AppCompatActivity(), View.OnClickListener {
    private val activitiesTypes: Array<String> =
        arrayOf("Btt", "Estrada", "Rolo", "Corrida", "Trail", "Passadeira", "Natação", "Ginásio")
    private val activitiesTypesList: List<String> = listOf(*activitiesTypes)

    private lateinit var tuesTraining: TextView;
    private lateinit var wedTraining: TextView;
    private lateinit var thursTraining: TextView;
    private lateinit var friTraining: TextView;
    private lateinit var satTraining: TextView;
    private lateinit var sendButton: Button;

    private var tuesSelectedActivities: BooleanArray = BooleanArray(activitiesTypes.size)
    private var wedSelectedActivities: BooleanArray = BooleanArray(activitiesTypes.size)
    private var thursSelectedActivities: BooleanArray = BooleanArray(activitiesTypes.size)
    private var fridSelectedActivities: BooleanArray = BooleanArray(activitiesTypes.size)
    private var satSelectedActivities: BooleanArray = BooleanArray(activitiesTypes.size)

    private var messageToSent : String = "";
    private lateinit var mondayTraining : String;


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState);
        setContentView(R.layout.availability_layout);
        val bundle= getIntent().getExtras();

        val string : String? = bundle?.getString("tssValue")
        if (string != null) {
            mondayTraining = string
        }

        tuesTraining = findViewById<TextView>(R.id.tuesdayTrainingType);
        wedTraining = findViewById<TextView>(R.id.wednesdayTrainingType);
        thursTraining= findViewById<TextView>(R.id.thursdayTrainingType);
        friTraining = findViewById<TextView>(R.id.fridayTrainingType);
        satTraining = findViewById<TextView>(R.id.saturdayTrainingType);
        sendButton= findViewById<Button>(R.id.availabilitySendButton)


        tuesTraining.setOnClickListener {
            tuesdayTraining(tuesTraining);
        }

        wedTraining.setOnClickListener {
            wednesdayTraining(wedTraining);
        }

        thursTraining.setOnClickListener {
            thursdayTraining(thursTraining);
        }

        friTraining.setOnClickListener {
            fridayTraining(friTraining)
        }

        satTraining.setOnClickListener {
            saturdayTraining(satTraining)
        }

        sendButton.setOnClickListener(this)

    }

    fun tuesdayTraining(textView: TextView){
        val builder = AlertDialog.Builder(this@AvailabilityScreen)

        builder.setTitle("Escolhe o tipo de treino que podes fazer terça-feira")

        builder.setMultiChoiceItems(
            activitiesTypes,
            tuesSelectedActivities
        ) { dialog, wich, iscCHecked ->
            tuesSelectedActivities[wich] = iscCHecked;
            val currentItem = activitiesTypesList[wich];
            Toast.makeText(
                applicationContext,
                currentItem + " " + iscCHecked,
                Toast.LENGTH_SHORT
            ).show();
        }
        builder.setPositiveButton("OK") { dialog, which ->
            textView.text = "Preenchido";
        }

        builder.setNeutralButton("Cancelar") { dialog, which ->
            dialog.dismiss();
        }
        val dialog = builder.create()
        dialog.show()
    }

    fun wednesdayTraining(textView: TextView){
        val builder = AlertDialog.Builder(this@AvailabilityScreen)

        builder.setTitle("Escolhe o tipo de treino que podes fazer quarta-feira")

        builder.setMultiChoiceItems(
            activitiesTypes,
            wedSelectedActivities
        ) { dialog, wich, iscCHecked ->
            wedSelectedActivities[wich] = iscCHecked;
            val currentItem = activitiesTypesList[wich];
            Toast.makeText(
                applicationContext,
                currentItem + " " + iscCHecked,
                Toast.LENGTH_SHORT
            ).show();
        }
        builder.setPositiveButton("OK") { dialog, which ->
            textView.text = "Preenchido";
        }

        builder.setNeutralButton("Cancelar") { dialog, which ->
            dialog.dismiss();
        }
        val dialog = builder.create()
        dialog.show()
    }

    fun thursdayTraining(textView: TextView){
        val builder = AlertDialog.Builder(this@AvailabilityScreen)

        builder.setTitle("Escolhe o tipo de treino que podes fazer quinta-feira")

        builder.setMultiChoiceItems(
            activitiesTypes,
            thursSelectedActivities
        ) { dialog, wich, iscCHecked ->
            thursSelectedActivities[wich] = iscCHecked;
            val currentItem = activitiesTypesList[wich];
            Toast.makeText(
                applicationContext,
                currentItem + " " + iscCHecked,
                Toast.LENGTH_SHORT
            ).show();
        }
        builder.setPositiveButton("OK") { dialog, which ->
            textView.text = "Preenchido";
        }

        builder.setNeutralButton("Cancelar") { dialog, which ->
            dialog.dismiss();
        }
        val dialog = builder.create()
        dialog.show()
    }

    fun fridayTraining(textView: TextView){
        val builder = AlertDialog.Builder(this@AvailabilityScreen)

        builder.setTitle("Escolhe o tipo de treino que podes fazer sexta-feira")

        builder.setMultiChoiceItems(
            activitiesTypes,
            fridSelectedActivities
        ) { dialog, wich, iscCHecked ->
            fridSelectedActivities[wich] = iscCHecked;
            val currentItem = activitiesTypesList[wich];
            Toast.makeText(
                applicationContext,
                currentItem + " " + iscCHecked,
                Toast.LENGTH_SHORT
            ).show();
        }
        builder.setPositiveButton("OK") { dialog, which ->
            textView.text = "Preenchido";
        }

        builder.setNeutralButton("Cancelar") { dialog, which ->
            dialog.dismiss();
        }
        val dialog = builder.create()
        dialog.show()
    }

    fun saturdayTraining(textView: TextView){
        val builder = AlertDialog.Builder(this@AvailabilityScreen)

        builder.setTitle("Escolhe o tipo de treino que podes fazer sábado")

        builder.setMultiChoiceItems(
            activitiesTypes,
            satSelectedActivities
        ) { dialog, wich, isChecked ->
            satSelectedActivities[wich] = isChecked;
            val currentItem = activitiesTypesList[wich];
            Toast.makeText(
                applicationContext,
                currentItem + " " + isChecked,
                Toast.LENGTH_SHORT
            ).show();
        }
        builder.setPositiveButton("OK") { dialog, which ->
            textView.text = "Preenchido";
        }

        builder.setNeutralButton("Cancelar") { dialog, which ->
            dialog.dismiss();
        }
        val dialog = builder.create()
        dialog.show()
    }



    fun checkParameters():Boolean{
        var tuesdayTime = -1;
        var wednesdayTime = -1;
        var thursdayTime = -1;
        var fridayTime = -1;
        var saturdayTime = -1;

        if (findViewById<EditText>(R.id.tuesdayTimeValue).text.isNotEmpty()) {
            tuesdayTime = findViewById<EditText>(R.id.tuesdayTimeValue).text.toString().toInt();
        }
        if(findViewById<EditText>(R.id.wednesdayTimeValue).text.isNotEmpty()) {
            wednesdayTime = findViewById<EditText>(R.id.wednesdayTimeValue).text.toString().toInt();
        }
        if (findViewById<EditText>(R.id.thursdayTimeValue).text.isNotEmpty()) {
            thursdayTime = findViewById<EditText>(R.id.thursdayTimeValue).text.toString().toInt();
        }
        if (findViewById<EditText>(R.id.fridayTimeValue).text.isNotEmpty()) {
            fridayTime = findViewById<EditText>(R.id.fridayTimeValue).text.toString().toInt();
        }
        if (findViewById<EditText>(R.id.saturdayTimeValue).text.isNotEmpty()) {
            saturdayTime = findViewById<EditText>(R.id.saturdayTimeValue).text.toString().toInt();
        }

        messageToSent = "Bom dia Sérgio, \nO treino de segunda é: ${mondayTraining}.\n\n";

        if (mondayTraining.equals("Bloco Z2 ou Descanso")){
            messageToSent = "Bom dia Sérgio, \nO treino de segunda é: Bloco Z2.\n\n";
        }
        if (findViewById<Switch>(R.id.mondayRest).isChecked){
            messageToSent = "Bom dia Sérgio, \nO treino de segunda é: descanso.\n\n";

        }

        if ((tuesSelectedActivities.contains(true) && tuesdayTime <1) || (!tuesSelectedActivities.contains(true) && tuesdayTime >0)) {
            Util.showMessage("verifica os valores de terça-feira", applicationContext)
            return false
        }

        if ((wedSelectedActivities.contains(true) && wednesdayTime <1) || (!wedSelectedActivities.contains(true) && wednesdayTime >0)) {
            Util.showMessage("verifica os valores de quarta-feira", applicationContext)
            return false
        }

        if ((thursSelectedActivities.contains(true) && thursdayTime <1) || (!thursSelectedActivities.contains(true) && thursdayTime >0)) {
            Util.showMessage("verifica os valores de quinta-feira", applicationContext)
            return false
        }

        if ((fridSelectedActivities.contains(true) && fridayTime <1) || (!fridSelectedActivities.contains(true) && fridayTime >0)) {
            Util.showMessage("verifica os valores de sexta-feira", applicationContext)
            return false
        }

        if ((satSelectedActivities.contains(true) && saturdayTime <1) || (!satSelectedActivities.contains(true) && saturdayTime >0)) {
            Util.showMessage("verifica os valores de sábado", applicationContext)
            return false
        }

        buildFinalMessage(tuesdayTime, wednesdayTime, thursdayTime, fridayTime, saturdayTime)

        return true
    }

    private fun getTypeOfTraining(boolenArray: BooleanArray): Any {
        var finalString : String = ""
        for(i in boolenArray.indices ){
            if (boolenArray[i]){
                finalString = finalString.plus(activitiesTypes[i]).plus(", ")
            }
        }
        finalString = finalString.removeSuffix(", ")

        return finalString
    }

    override fun onClick(v: View?) {
        if (v != null){
            if (v.id == R.id.availabilitySendButton){
                if (checkParameters()){
                    val sendIntent: Intent = Intent().apply {
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_TEXT, messageToSent)
                        type = "text/plain"
                    }
                    val shareIntent : Intent = Intent.createChooser(sendIntent, null);
                    startActivity(shareIntent);


                }
            }
        }
    }

    fun buildFinalMessage(tuesdayTime:Int, wednesdayTime:Int, thursdayTime:Int, fridayTime:Int, saturdayTime:Int) {
        if(tuesdayTime > 0) {
            val typeOfTraining = getTypeOfTraining(tuesSelectedActivities)
            val tuesdayMessage : String = "Terça feira: ${tuesdayTime} minutos de: ${typeOfTraining}\n\n"
            messageToSent = messageToSent.plus(tuesdayMessage)
        } else{
            val tuesdayMessage : String = "Terça feira: sem possibilidade de treinar\n\n"
            messageToSent = messageToSent.plus(tuesdayMessage)
        }

        if(wednesdayTime > 0) {
            val typeOfTraining = getTypeOfTraining(wedSelectedActivities)
            val wednesdayMessage : String = "Quarta feira: ${wednesdayTime} minutos de: ${typeOfTraining}\n\n"
            messageToSent = messageToSent.plus(wednesdayMessage)
        } else{
            val wednesdayMessage : String = "Quarta feira: sem possibilidade de treinar\n\n"
            messageToSent = messageToSent.plus(wednesdayMessage)
        }

        if(thursdayTime > 0) {
            val typeOfTraining = getTypeOfTraining(thursSelectedActivities)
            val thursdayMessage : String = "Quinta feira: ${thursdayTime} minutos de: ${typeOfTraining}\n\n"
            messageToSent = messageToSent.plus(thursdayMessage)
        } else{
            val thursdayMessage : String = "Quinta feira: sem possibilidade de treinar\n\n"
            messageToSent = messageToSent.plus(thursdayMessage)
        }

        if(fridayTime > 0) {
            val typeOfTraining = getTypeOfTraining(fridSelectedActivities)
            val fridayMessage : String = "Sexta feira: ${fridayTime} minutos de: ${typeOfTraining}\n\n"
            messageToSent = messageToSent.plus(fridayMessage)
        } else{
            val fridayMessage : String = "Sexta feira: sem possibilidade de treinar\n\n"
            messageToSent = messageToSent.plus(fridayMessage)
        }

        if(saturdayTime > 0) {
            val typeOfTraining = getTypeOfTraining(satSelectedActivities)
            val saturdayMessage : String = "Sábado: ${saturdayTime} minutos de: ${typeOfTraining}\n\n"
            messageToSent = messageToSent.plus(saturdayMessage)
        } else{
            val saturdayMessage : String = "Sábado: sem possibilidade de treinar\n\n"
            messageToSent = messageToSent.plus(saturdayMessage)
        }

    }

}