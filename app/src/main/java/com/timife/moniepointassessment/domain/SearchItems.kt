package com.timife.moniepointassessment.domain

data class Item(
    val name: String,
    val number: String,
    val from: String,
    val to: String
)

val searchItems = listOf(
    Item("MacBook pro M2", "#NE43857340857904", "Paris", "Morocco"),
    Item("Summer linen jacket", "#NEJ20089934122231", "Barcelona", "Paris"),
    Item("Tapered-fit jeans AW", "#NEJ35870264978659", "Columbia", "Paris"),
    Item("Slim fit jeans AW", "#NEJ35870264978659", "Bogota", "Dhaka"),
    Item("Office setup desk", "#NEJ23481570754963", "France", "Germany"),
    Item("Straight fit jacket", "#NE23749242463773", "Germany", "Russia"),
)