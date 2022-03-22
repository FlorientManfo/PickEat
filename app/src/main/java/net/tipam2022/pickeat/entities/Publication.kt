package net.tipam2022.pickeat.entities

data class Publication(var meal: Meal, var description: String, var image: Int, val rate: Double = 0.0, val price: Double){
}