package com.example.epifi.activities

import android.content.Intent
import android.graphics.Camera
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.example.epifi.R
import com.example.epifi.application.EpifiApplication
import com.example.epifi.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(){
    private lateinit var binding: ActivityMainBinding  // viewbinding object for MainActivity

    lateinit var epifiApplication: EpifiApplication // application class object

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Hide the status bar.


        binding = ActivityMainBinding.inflate(layoutInflater)   // initialisation of viewbinding object
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        epifiApplication=application as EpifiApplication
        //window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        supportActionBar!!.hide()



    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }



    override fun onResume() {
        super.onResume()
        Log.d("Here", "OnResume MainActivity")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Here", "onStop MainActivity")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Here", "onPause MainActivity")
    }

    override fun onStart() {
        super.onStart()
        Log.d("Here", "onStart MainActivity")
    }


}