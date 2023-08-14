package com.example.contacts.model

data class Contact(var id: Int, var name:String, var phoneNumber: String) {
    constructor(name: String, phoneNumber: String): this(-1,name,phoneNumber)
    constructor() : this(-1,"","")
}
