package com.empo.android.asynctest

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Variable to hold service class name
        val serviceClass = RandomNumberService::class.java

        // Initialize a new Intent instance
        val intent = Intent(applicationContext, serviceClass)


        // Button to start the service
        button_start.setOnClickListener{
            // If the service is not running then start it
            if (!isServiceRunning(serviceClass)) {
                // Start the service
                startService(intent)
            } else {
                toast("Service already running.")
            }
        }


        // Button to stop the service
        button_stop.setOnClickListener{
            // If the service is not running then start it
            if (isServiceRunning(serviceClass)) {
                // Stop the service
                stopService(intent)
            } else {
                toast("Service already stopped.")
            }
        }


        // Get the service status
        button_stats.setOnClickListener{
            if (isServiceRunning(serviceClass)) {
                toast("Service is running.")
            } else {
                toast("Service is stopped.")
            }
        }
    }


    // Custom method to determine whether a service is running
    private fun isServiceRunning(serviceClass: Class<*>): Boolean {

        val activityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager

        // Loop through the running services
        for (service in activityManager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.name == service.service.className) {
                // If the service is running then return true
                return true
            }
        }
        return false
    }
}



// Extension function to show toast message
fun Context.toast(message:String){
    Toast.makeText(applicationContext,message,Toast.LENGTH_SHORT).show()
}