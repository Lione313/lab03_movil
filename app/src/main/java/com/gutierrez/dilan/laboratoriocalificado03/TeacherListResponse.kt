package com.gutierrez.dilan.laboratoriocalificado03

// TeacherListResponse.kt


data class TeacherListResponse(
    val count: Int,
    val next: String,
    val results: List<TeacherResponse>? // Esto es importante para asegurar que sea nulo o no
)
