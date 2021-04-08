package com.example.epifi.utils

import androidx.lifecycle.MutableLiveData

data class AllValid(var isPanValid: Boolean, var isDateValid:Boolean, var isMonthValid:Boolean, var isYearValid:Boolean){
    // check to, if all value of boolean is true
     fun isAllValid():Boolean{
        return isPanValid && isDateValid && isMonthValid && isYearValid
    }
}
