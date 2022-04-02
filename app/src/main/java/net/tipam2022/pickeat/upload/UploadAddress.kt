package net.tipam2022.pickeat.upload

class UploadAddress {
    var city: String = String()
    var district: String = String()

    constructor()
    constructor(city: String, district: String){
        this.city = city
        this.district = district
    }
}