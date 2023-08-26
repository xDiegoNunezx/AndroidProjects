package com.example.weatherapp

data class WeatherResponse(
    val coord: Coord,
    val weather: MutableList<Wheather>,
    val base: String,
    val main: Main,
    val visibility: Int,
    val wind: Wind,
    val clouds: Clouds,
    val dt: Long,
    val sys: Sys,
    val timezone: Int,
    val id: Long,
    val name: String,
    val cod: Int
)