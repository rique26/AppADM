package com.example.appadm.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appadm.models.Medicamento
import com.example.appadm.R

class MedicamentoAdapter(private var medicamentos: List<Medicamento>) :
    RecyclerView.Adapter<MedicamentoAdapter.MedicamentoViewHolder>() {

    inner class MedicamentoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nomeMedicamento: TextView = itemView.findViewById(R.id.nome_medicamento)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicamentoViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.res_item_medicamento, parent, false)
        return MedicamentoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MedicamentoViewHolder, position: Int) {
        val currentItem = medicamentos[position]
        holder.nomeMedicamento.text = currentItem.produto
    }

    override fun getItemCount() = medicamentos.size

    fun updateList(newList: List<Medicamento>) {
        medicamentos = newList
        notifyDataSetChanged()
    }
}

