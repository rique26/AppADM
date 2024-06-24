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


//        init {
//            btnMedicamento.setOnClickListener {
//                val medicamento = medicamentos[adapterPosition]
//                itemClickListener.onItemClick(medicamento)
//
//                // Define a cor de fundo quando o botão é pressionado
//                val colorFrom = Color.TRANSPARENT
//                val colorTo = itemView.context.resources.getColor(R.color.black) // Substitua pela sua cor de seleção
//                val colorAnimation = ValueAnimator.ofObject(ArgbEvaluator(), colorFrom, colorTo)
//                colorAnimation.duration = 1400 // Tempo da animação em milissegundos
//
//                colorAnimation.addUpdateListener { animator ->
//                    btnMedicamento.setBackgroundColor(animator.animatedValue as Int)
//                }
//
//                colorAnimation.start()
//
//                // Adicione um atraso para a mudança de cor de volta para a cor padrão
//                val handler = Handler(Looper.getMainLooper())
//                handler.postDelayed({
//                    // Define a cor de fundo padrão quando o botão não está mais pressionado
//                    btnMedicamento.setBackgroundResource(android.R.color.transparent)
//                }, 1500)
//            }
//        }
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
