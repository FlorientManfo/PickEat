package net.tipam2022.pickeat.entities

import java.text.DateFormat
import java.text.DateFormat.getDateInstance

data class PublicationModel(var mealModel: MealModel, var description: String, var image: Int, val rate: Double = 0.0, val price: Double, val publicationHours: DateFormat = getDateInstance()){
}