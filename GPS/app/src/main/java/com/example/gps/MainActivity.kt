package com.example.gps

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.LocationManager
import android.net.Uri
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var textViewLongitude: TextView
    private lateinit var textViewLatitude: TextView
    private lateinit var textViewGeo: TextView
    private lateinit var btnOnSMS: Button
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var locationCallbck: LocationCallback
    companion object {
        private const val REQUEST_LOCATION_CODE = 101
        private const val DEFAULT_MOBILE_NUMBER = "+01068555223"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textViewLatitude = findViewById(R.id.tv_latitude_val)
        textViewLongitude = findViewById(R.id.tv_longitude_val)
        textViewGeo = findViewById(R.id.tv_geo)
        btnOnSMS = findViewById(R.id.btn_onSMS)
        btnOnSMS.setOnClickListener{
            sendSMSWithLocation(textViewGeo.text.toString(), DEFAULT_MOBILE_NUMBER)
        }
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
    }

    override fun onStart() {
        super.onStart()

        if (checkPermissions()) {
            if (isLocationEnabled()) {
                getFreshLocation()
            } else {
                enableLocationService()
            }
        } else {
            requestLocationPermission()
        }
    }

    private fun checkPermissions(): Boolean {
        return (ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED)
    }

    private fun requestLocationPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION),
            REQUEST_LOCATION_CODE
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_LOCATION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getFreshLocation()
            } else {
                Toast.makeText(this, "Location permission denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun isLocationEnabled(): Boolean {
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    }

    private fun enableLocationService() {
        Toast.makeText(this, "Please turn on location services", Toast.LENGTH_SHORT).show()
        val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
        startActivity(intent)
    }

    private fun getFreshLocation() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestLocationPermission()
            return
        }

        val locationRequest = LocationRequest.Builder(10000)
            .setPriority(Priority.PRIORITY_HIGH_ACCURACY)
            .build()

        fusedLocationProviderClient.requestLocationUpdates(
            locationRequest,
            object : LocationCallback() {
                override fun onLocationResult(locationResult: LocationResult) {
                    super.onLocationResult(locationResult)
                    val lastLocation = locationResult.lastLocation
                    if (lastLocation != null) {
                        textViewLongitude.text = lastLocation.longitude.toString()
                        textViewLatitude.text = lastLocation.latitude.toString()
                        getAddressFromLocation(lastLocation.latitude, lastLocation.longitude)
                    }
                }
            },
            Looper.getMainLooper()
        )
    }

    private fun getAddressFromLocation(latitude: Double, longitude: Double) {
        val geocoder = Geocoder(this, Locale.getDefault())
        try {
            val addressList = geocoder.getFromLocation(latitude, longitude, 1)
            if (addressList != null && addressList.isNotEmpty()) {
                val address = addressList[0]
                textViewGeo.text = address.getAddressLine(0)
            } else {
                textViewGeo.text = "Address not found"
            }
        } catch (e: Exception) {
            textViewGeo.text = "Geocoder error: ${e.message}"
            Log.e("Geocoder", "Error in getting address: ${e.message}")
        }
    }

    private fun sendSMSWithLocation(message: String, phoneNumber: String) {
        try {
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("smsto:$phoneNumber")
                putExtra("sms_body", "My location address: $message")
            }
            startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(this, "Unable to open SMS app: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

//    override fun onPause() {
//        super.onPause()
//        stopLocationUpdates()
//    }

//    private fun stopLocationUpdates() {
//        fusedLocationProviderClient.removeLocationUpdates(LocationCallback)
//    }

}
