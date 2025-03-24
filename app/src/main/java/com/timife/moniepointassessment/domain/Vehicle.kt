package com.timife.moniepointassessment.domain

import com.timife.moniepointassessment.R

data class Vehicle(
    val name: String,
    val type: String,
    val image: Int
)
val vehicles = listOf(
    Vehicle("Ocean freight", "International", R.drawable.new_ship),
    Vehicle("Cargo freight", "Reliable", R.drawable.new_truck),
    Vehicle("Air freight", "International", R.drawable._plane)
)