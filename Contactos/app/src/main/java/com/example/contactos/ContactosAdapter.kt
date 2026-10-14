package com.example.contactos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.contactos.databinding.ItemContactoBinding

class ContactosAdapter(private val contactos: List<Contacto>) :
    RecyclerView.Adapter<ContactosAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = ItemContactoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contacto = contactos[position]
        holder.bind(contacto)
    }

    override fun getItemCount(): Int {
        return contactos.size
    }

    class ViewHolder(private val binding: ItemContactoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(contacto: Contacto) {
            binding.tvNombre.text = contacto.nombre
            binding.tvTelefono.text = contacto.telefono
            // AQUÍ SE MUESTRA LA IMAGEN: Modifica o pasa el recurso drawable que desees mostrar (ej. R.drawable.tu_imagen)
            binding.imageView.setImageResource(contacto.imagen)
        }
    }
}
