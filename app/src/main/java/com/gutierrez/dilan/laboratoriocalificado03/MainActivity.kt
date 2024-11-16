package com.gutierrez.dilan.laboratoriocalificado03

// MainActivity.kt



import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.gutierrez.dilan.laboratoriocalificado03.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val teacherAdapter = TeacherAdapter(listOf(), ::onTeacherClick, ::onTeacherLongClick)

        // Setup RecyclerView
        binding.rvTeachers.layoutManager = LinearLayoutManager(this)
        binding.rvTeachers.adapter = teacherAdapter

        // Observe the data from ViewModel
        mainViewModel.teacherList.observe(this, { teachers ->
            if (teachers != null) {
                teacherAdapter.updateTeachers(teachers)
            }
            binding.progressBar.visibility = View.GONE
        })

        // Observe loading state
        mainViewModel.isLoading.observe(this, { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        })

        // Observe errors
        mainViewModel.errorApi.observe(this, { error ->
            Toast.makeText(this, error, Toast.LENGTH_LONG).show()
        })
    }

    private fun onTeacherClick(teacher: TeacherResponse) {
        // Click simple: Llamar al número del profesor
        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${teacher.phone_number}"))
        startActivity(intent)
    }

    private fun onTeacherLongClick(teacher: TeacherResponse): Boolean {
        // Click largo: Enviar un correo
        val intent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:${teacher.email}"))
        startActivity(intent)
        return true
    }
}
