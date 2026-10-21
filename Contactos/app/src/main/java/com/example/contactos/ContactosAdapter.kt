package com.example.contactos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContactosAdapter(
    private val contactos: List<Contacto>,
    private val onInfoClicked: (Contacto) -> Unit,
    private val onCallClicked: (Contacto) -> Unit
) : RecyclerView.Adapter<ContactosAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_contacto, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contacto = contactos[position]

        holder.imageView.setImageResource(contacto.imagen)
        holder.tvNombre.text = contacto.nombre
        holder.tvTelefono.text = contacto.telefono

        holder.botonInfo.setOnClickListener {
            onInfoClicked(contacto)
        }

        holder.botonLlamar.setOnClickListener {
            onCallClicked(contacto)
        }
    }

    override fun getItemCount(): Int {
        return contactos.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val tvNombre: TextView = itemView.findViewById(R.id.tvNombre)
        val tvTelefono: TextView = itemView.findViewById(R.id.tvTelefono)
        val botonInfo: Button = itemView.findViewById(R.id.botonInfo)
        val botonLlamar: Button = itemView.findViewById(R.id.botonLlamar)
    }
}
