package net.tipam2022.pickeat.entities

data class MenuModel (var title: String, var category: String, var region: String, var description: String, var photo: Int, var publicationModels: ArrayList<PublicationModel>? = null) {
}