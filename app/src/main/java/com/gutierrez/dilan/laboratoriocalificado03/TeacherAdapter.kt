package com.gutierrez.dilan.laboratoriocalificado03


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gutierrez.dilan.laboratoriocalificado03.databinding.ItemTeacherBinding

class TeacherAdapter(
    private var teachers: List<TeacherResponse>,
    private val onTeacherClick: (TeacherResponse) -> Unit,
    private val onTeacherLongClick: (TeacherResponse) -> Boolean
) : RecyclerView.Adapter<TeacherAdapter.TeacherViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeacherViewHolder {
        val binding = ItemTeacherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TeacherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TeacherViewHolder, position: Int) {
        val teacher = teachers[position]
        holder.bind(teacher)
    }

    override fun getItemCount(): Int = teachers.size

    // Actualiza la lista de profesores en el adaptador
    fun updateTeachers(newTeachers: List<TeacherResponse>) {
        teachers = newTeachers
        notifyDataSetChanged() // Notifica al adaptador que los datos han cambiado
    }

    inner class TeacherViewHolder(private val binding: ItemTeacherBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(teacher: TeacherResponse) {
            // Asignar el nombre completo del docente
            binding.tvTeacherName.text = teacher.getFullName()

            // Asignar el teléfono del docente
            binding.tvTeacherPhone.text = "Teléfono: ${teacher.phone_number}"

            // Asignar el correo electrónico del docente
            binding.tvTeacherEmail.text = "Email: ${teacher.email}"

            // Si tienes una URL de imagen, cargarla en el ImageView
            // Suponiendo que usas una librería como Glide o Picasso
            Glide.with(binding.ivTeacherImage.context)
                .load(teacher.image_url)  // Aquí debes asegurarte de tener la URL de la imagen
                .into(binding.ivTeacherImage)

            // Configurar los clicks
            binding.root.setOnClickListener { onTeacherClick(teacher) }
            binding.root.setOnLongClickListener { onTeacherLongClick(teacher) }
        }
    }
}
