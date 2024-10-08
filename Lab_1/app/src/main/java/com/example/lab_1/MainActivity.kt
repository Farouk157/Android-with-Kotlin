package com.example.lab_1

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), Communicator {

    var isLandscape: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        isLandscape = resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.product_list_frag, FragmentA())
                .commit()
        }
    }

    override fun passDataToActivity2(clubDetails: String) {
        if (isLandscape){
            val fragmentB = FragmentB()
            val bundle = Bundle()
            bundle.putString("clubDetails", clubDetails)
            fragmentB.arguments = bundle

            supportFragmentManager.beginTransaction()
                .replace(R.id.product_details_list, fragmentB)
                .addToBackStack(null)
                .commit()
        } else {
            val intent = Intent(this, MainActivity2::class.java)
            intent.putExtra("clubDetails", clubDetails)
            startActivity(intent)
        }
    }

}
