package com.example.appadm.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appadm.R
import com.example.appadm.database.models.SintomaControle
import com.example.appadm.models.Sintoma

class TelaControleInicialAdapter(private var sintomas: List<SintomaControle>):
    RecyclerView.Adapter<TelaControleInicialAdapter.TelaControleInicialViewHolder>() {

    inner class TelaControleInicialViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val disposicao: TextView = itemView.findViewById(R.id.disposicao)
        val horario: TextView = itemView.findViewById(R.id.horario)
        val sintoma: TextView = itemView.findViewById(R.id.sintoma)
        val dia: TextView = itemView.findViewById(R.id.dia)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TelaControleInicialViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.res_item_controle, parent, false)
        return TelaControleInicialViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TelaControleInicialViewHolder, position: Int) {
        val currentItem = sintomas[position]
        holder.disposicao.text = currentItem.disposicao
        holder.horario.text = currentItem.horario
        holder.sintoma.text = currentItem.sintoma
        holder.dia.text = currentItem.dia
    }

    override fun getItemCount() = sintomas.size

    fun updateList(newList: List<SintomaControle>) {
        sintomas = newList
        notifyDataSetChanged()
    }
}
