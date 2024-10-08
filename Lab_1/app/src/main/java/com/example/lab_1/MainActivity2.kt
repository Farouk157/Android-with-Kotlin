// MainActivity2.kt (Activity 2)
package com.example.lab_1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val clubDetails = intent.getStringExtra("clubDetails")

        val fragmentB = FragmentB()
        val bundle = Bundle()
        bundle.putString("clubDetails", clubDetails)
        fragmentB.arguments = bundle

        supportFragmentManager.beginTransaction()
            .replace(R.id.product_details_list, fragmentB)
            .commit()
    }
}
