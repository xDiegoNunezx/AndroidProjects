package com.example.fragmentscommunication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fragmentscommunication.fragments.FragmentA
import com.example.fragmentscommunication.fragments.FragmentB

class MainActivity : AppCompatActivity(), FragmentA.FragmentAListener, FragmentB.FragmentBListener {
    private lateinit var fragmentA: FragmentA
    private lateinit var fragmentB: FragmentB
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fragmentA = FragmentA()
        fragmentB = FragmentB()

        supportFragmentManager.beginTransaction()
            .replace(R.id.container_b, fragmentB)
            .replace(R.id.container_a, fragmentA)
            .commit()
    }

    override fun sendFromA(input: String) {
        fragmentB.updateText(input)
    }

    override fun sendFromB(input: String) {
        fragmentA.updateText(input)
    }
}