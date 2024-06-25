package com.example.appadm.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appadm.R
import com.example.appadm.database.models.MedicineReminder

class MedicineReminderAdapter(private var medicamentos: List<MedicineReminder>) :
    RecyclerView.Adapter<MedicineReminderAdapter.MedicamentoAgendaViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(medicamento: MedicineReminder)
    }
    inner class MedicamentoAgendaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val btnMedicamento: Button = itemView.findViewById(R.id.bt_item_medicamentos2)
        val txt1: TextView = itemView.findViewById(R.id.txt_1)
        val txt2: TextView = itemView.findViewById(R.id.txt_2)
        val txt3: TextView = itemView.findViewById(R.id.txt_3)
        val txt4: TextView = itemView.findViewById(R.id.txt_4)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicamentoAgendaViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.res_scheduled_medicine_item, parent, false)
        return MedicamentoAgendaViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MedicamentoAgendaViewHolder, position: Int) {
        val currentItem = medicamentos[position]
        holder.btnMedicamento.text = currentItem.nome_medicamento
        holder.txt1.text = currentItem.horario1
        holder.txt2.text = currentItem.horario2
        holder.txt3.text = currentItem.horario3
        holder.txt4.text = currentItem.horario4
    }

    override fun getItemCount() = medicamentos.size

    fun updateList(newList: List<MedicineReminder>) {
        medicamentos = newList
        notifyDataSetChanged()
    }

}
