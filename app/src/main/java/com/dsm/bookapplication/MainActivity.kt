package com.dsm.bookapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dsm.bookapplication.view.HomeFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        supportFragmentManager.beginTransaction()
            .replace(R.id.container, HomeFragment())
            .commit()
    }

}
