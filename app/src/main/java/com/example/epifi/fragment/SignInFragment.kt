package com.example.epifi.fragment



import android.os.Bundle
import android.util.Log

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import androidx.fragment.app.Fragment

import androidx.navigation.navGraphViewModels
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.example.epifi.R
import com.example.epifi.application.EpifiApplication
import com.example.epifi.databinding.SigninFragmentBinding

import com.example.epifi.viewmodels.SignInViewModel


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */

class SignInFragment : Fragment(),  View.OnClickListener {
    private var firstFragmentBinding: SigninFragmentBinding? = null

    lateinit var epifiApplication: EpifiApplication



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
        epifiApplication = activity?.application as EpifiApplication

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        Log.d("Here","OnStart LoginFragment")

    }

    override fun onStop() {
        super.onStop()
        Log.d("Here","OnStop LoginFragment")

    }

    override fun onResume() {
        super.onResume()
        Log.d("Here","OnResume LoginFragment")
    }



    override fun onDestroyView() {
        firstFragmentBinding = null
        super.onDestroyView()
    }



    override fun onClick(v: View?) {
        YoYo.with(Techniques.Pulse).duration(500).playOn(v as View)
        when (v) {

        }
    }


}




