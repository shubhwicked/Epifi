package com.example.epifi.viewmodels


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.epifi.utils.AllValid


class SignInViewModel : ViewModel() {
    //Mutable live data for checking the validation of all editetxt. through the boolean
    private val isValid:MutableLiveData<AllValid> = MutableLiveData(AllValid(isPanValid = false,isDateValid = false,isMonthValid = false,isYearValid = false))

     fun getIsValid():MutableLiveData<AllValid> = isValid

    // it will set the value of live data, and return the current value to UI.
     fun setValid(isPanValid:Boolean,isDateValid:Boolean,isMonthValid:Boolean,isYearValid:Boolean){
         isValid.value?.isDateValid=isDateValid
         isValid.value?.isPanValid=isPanValid
         isValid.value?.isMonthValid=isMonthValid
         isValid.value?.isYearValid=isYearValid
         isValid.postValue(isValid.value)
     }

}



