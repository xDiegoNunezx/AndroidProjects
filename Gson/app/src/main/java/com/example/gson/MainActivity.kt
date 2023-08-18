package com.example.gson

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val gson = Gson()

        /*
        val employee = Employee("Alex",23,"alex@gmail.com")
        val json = gson.toJson(employee)
        Log.d("json",json)

         */

        val json = "{\"firstName\":\"Alex\",\"age\":23,\"mail\":alex@gmail.com\"}";
        val employee = gson.fromJson(json, Employee::class.java)
        Log.d("json",employee.toString())
    }
}