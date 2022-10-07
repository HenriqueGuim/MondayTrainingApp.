package com.example.mondaytraining

import android.content.Context
import android.widget.Toast

class Util {
    companion object{
        fun showMessage(string: String, applicationContext:Context){
            Toast.makeText(
                applicationContext,
                string,
                Toast.LENGTH_SHORT
            ).show();
        }
    }
}

