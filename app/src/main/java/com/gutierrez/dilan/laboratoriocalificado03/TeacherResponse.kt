package com.gutierrez.dilan.laboratoriocalificado03



data class TeacherResponse(
    val name: String,
    val last_name: String,
    val phone_number: String,
    val email: String,
    val image_url: String
) {


    fun getFullName(): String = "$name $last_name"


    fun getImageUrl(): String = image_url


    fun getPhoneNumber(): String = phone_number



}
