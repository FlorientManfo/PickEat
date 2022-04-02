package net.tipam2022.pickeat.upload

import net.tipam2022.pickeat.entities.MenuModel
import net.tipam2022.pickeat.entities.UserModel

class UploadMenu {
    var name: String = String()
    var description: String = String()
    var photo: String = String()
    var category: UploadCategory = UploadCategory()
    var note: Double = 0.0

    constructor()
    constructor(name: String, description: String, photo: String
    ,category: UploadCategory, note: Double){
        this.name = name
        this.description = description
        this.photo = photo
        this.category = category
        this.note = note
    }
}