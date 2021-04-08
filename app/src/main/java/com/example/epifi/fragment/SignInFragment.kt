package com.example.epifi.fragment



import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.text.format.DateFormat.getDateFormat
import android.util.Log

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.adapters.TextViewBindingAdapter

import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer

import androidx.navigation.navGraphViewModels
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.example.epifi.R
import com.example.epifi.application.EpifiApplication
import com.example.epifi.databinding.SigninFragmentBinding
import com.example.epifi.utils.DateValidator
import com.example.epifi.utils.TextValidator

import com.example.epifi.viewmodels.SignInViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit
import java.util.regex.Pattern


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */

class SignInFragment : Fragment(),  View.OnClickListener {
    private var firstFragmentBinding: SigninFragmentBinding?=null



    /**
     * Boolean for each edittext validation
     */
    var isPanValid:Boolean= false
    var isDateValid:Boolean= false
    var isMonthValid:Boolean= false
    var isYearValid:Boolean= false
    var next: TextView?=null
    var idont: TextView?=null

    private val viewModel by navGraphViewModels<SignInViewModel>(R.id.SignInFragment) {
        defaultViewModelProviderFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = SigninFragmentBinding.inflate(inflater, container, false)
        firstFragmentBinding = binding


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        next=firstFragmentBinding!!.next
        next?.setOnClickListener(this)
        idont=firstFragmentBinding!!.idont
        idont?.setOnClickListener(this)


        //Observing livedata for setting next key enable or disable
        viewModel.getIsValid().observe(requireActivity(), Observer { o->
            if(o.isAllValid()){
                next?.setBackgroundResource(R.drawable.button)
                next?.isClickable=true
            }else{
                next?.setBackgroundResource(R.drawable.button1)
                next?.isClickable=false
            }

        } )

        /**
         * pancard text listener
         */
        firstFragmentBinding!!.pancard.filters = arrayOf(InputFilter.AllCaps(), InputFilter.LengthFilter(10))
        firstFragmentBinding!!.pancard.addTextChangedListener(object:TextValidator(firstFragmentBinding!!.pancard){
            override fun validate(textView: EditText?, text: String?) {
                //Regex to check alphanumeric for pan card
                val match= Pattern.compile("^[a-zA-Z0-9]*$").matcher(text)
                // pan card length is 10 character
              if(text?.length == 10 && match.matches()){

                  isPanValid =true
                  setValidToViewModel()
                  textView?.isSelected = true
              }else{
                  isPanValid = false
                  textView?.isSelected = false
                  setValidToViewModel()

              }
            }

        })
        /**
         * Date text listener
         */
        firstFragmentBinding!!.date.addTextChangedListener(object:TextValidator(firstFragmentBinding!!.date){
            override fun validate(textView: EditText?, text: String?) {
                //check if date is less than31 and the format is correct date of birth
                if(text!= "" && text?.length!! <=2 && text?.trim()?.toInt()!! in 1..31 && DateValidator.validateDate(getCorrectDateFormat())){
                        isDateValid=true
                        isMonthValid =true
                        isYearValid = true
                        textView?.isSelected=true

                }else{
                    isDateValid=false
                    textView?.isSelected=false

                }
                setValidToViewModel()
            }

        })

        /**
         * Month text change listener
         */
        firstFragmentBinding!!.month.addTextChangedListener(object:TextValidator(firstFragmentBinding!!.month){
            override fun validate(textView: EditText?, text: String?) {
                if(text!= "" && text?.length!! <=2 && text?.trim()?.toInt()!! in 1..12 && DateValidator.validateDate(getCorrectDateFormat())){
                    isDateValid=true
                    isMonthValid =true
                    isYearValid = true
                    textView?.isSelected = true
                }else{
                    isMonthValid = false
                    textView?.isSelected = false

                }
                setValidToViewModel()
            }

        })

        /**
         * Year text change listener
         */
        firstFragmentBinding!!.year.addTextChangedListener(object:TextValidator(firstFragmentBinding!!.year){
            override fun validate(textView: EditText?, text: String?) {
               if(text!= "" && text?.length==4 && text?.trim().toInt()>1900 && DateValidator.validateDate(getCorrectDateFormat())){
                   isDateValid=true
                   isMonthValid =true
                   isYearValid = true
                   textView?.isSelected = true
               }else{
                   isYearValid =false
                   textView?.isSelected = false

               }
                setValidToViewModel()
            }

        })
    }
    private fun showToast(message:String){
        Toast.makeText(activity,message,Toast.LENGTH_SHORT).show()
    }
    private fun getCorrectDateFormat(): String {
        //if date and month is of single digit, adding 0 as prefix.
        val month = if(firstFragmentBinding!!.month.text.length==1){ "0${firstFragmentBinding!!.month.text}"} else firstFragmentBinding!!.month.text
        val date = if(firstFragmentBinding!!.date.text.length==1){ "0${firstFragmentBinding!!.date.text}"} else firstFragmentBinding!!.date.text
        return "$month/$date/${firstFragmentBinding!!.year.text}"
    }

    fun setValidToViewModel(){
        // setting the data in viewmodel with accurate value
        viewModel.setValid(isPanValid=isPanValid,isDateValid=isDateValid,isMonthValid=isMonthValid,isYearValid=isYearValid)
    }




    override fun onDestroyView() {
        //setting viewbinding object null
        firstFragmentBinding = null
        super.onDestroyView()
    }



    override fun onClick(v: View?) {
        YoYo.with(Techniques.Pulse).duration(500).playOn(v as View)
        when (v) {
        next -> {
            showToast("Details Submitted Successfully")
            //using rxjava to delay app closing by 2 seconds
            Observable.timer(1,TimeUnit.SECONDS)
                    .map { activity?.supportFinishAfterTransition() }
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
        }
        idont -> {
            //closing activity
                activity?.supportFinishAfterTransition()
            }
        }
    }


}




