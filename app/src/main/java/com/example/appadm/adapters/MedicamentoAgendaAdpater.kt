package com.example.appadm.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.appadm.R
import com.example.appadm.database.models.MedicamentoAgenda
import com.example.appadm.models.Medicamento

class MedicamentoAgendaAdapter(private var medicamentos: List<MedicamentoAgenda>) :
    RecyclerView.Adapter<MedicamentoAgendaAdapter.MedicamentoAgendaViewHolder>() {

    inner class MedicamentoAgendaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nomeMedicamentoButton: Button = itemView.findViewById(R.id.bt_item_medicamentos)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicamentoAgendaViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.res_item_medicamento, parent, false)
        return MedicamentoAgendaViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MedicamentoAgendaViewHolder, position: Int) {
        val currentItem = medicamentos[position]
        holder.nomeMedicamentoButton.text = currentItem.nome_medicamento
        // Adicione aqui o clique do botão, se necessário
    }

    override fun getItemCount() = medicamentos.size


    fun updateList(newList: List<MedicamentoAgenda>) {
        medicamentos = newList
        notifyDataSetChanged()
    }

}
