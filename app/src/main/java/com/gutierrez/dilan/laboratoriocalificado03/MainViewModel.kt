package com.gutierrez.dilan.laboratoriocalificado03

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Response

class MainViewModel : ViewModel() {

    val teacherList = MutableLiveData<List<TeacherResponse>?>()
    val isLoading = MutableLiveData<Boolean>()
    val errorApi = MutableLiveData<String>()

    init {
        getAllTeachers()
    }

    private fun getAllTeachers() {
        isLoading.postValue(true)
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val call = getRetrofit().create(TeacherApi::class.java).getTeachers()
                if (call.isSuccessful) {
                    val teacherListResponse = call.body() // Guardamos la respuesta
                    // Verificamos si results no es nulo, y asignamos una lista vacía si es nulo
                    val teachers = teacherListResponse?.results ?: emptyList() // Si results es nulo, asignamos lista vacía
                    isLoading.postValue(false)
                    teacherList.postValue(teachers) // Asignamos la lista no nula
                } else {
                    errorApi.postValue("Error: ${call.code()} - ${call.message()}")
                    isLoading.postValue(false)
                }
            } catch (e: Exception) {
                errorApi.postValue("Exception: ${e.message}")
                isLoading.postValue(false)
            }
        }
    }



    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://private-effe28-tecsup1.apiary-mock.com/") // URL de la API
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
