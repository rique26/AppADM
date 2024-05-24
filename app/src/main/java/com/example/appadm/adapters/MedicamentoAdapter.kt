package com.example.appadm.adapters

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.content.Intent
import android.graphics.Color
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.appadm.models.Medicamento
import com.example.appadm.R
import com.example.appadm.TelaControleInicial

class MedicamentoAdapter(private var medicamentos: List<Medicamento>) :
    RecyclerView.Adapter<MedicamentoAdapter.MedicamentoViewHolder>() {



    inner class MedicamentoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val btnMedicamento: Button = itemView.findViewById(R.id.bt_item_medicamentos)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicamentoViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.res_item_medicamento, parent, false)
        return MedicamentoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MedicamentoViewHolder, position: Int) {
        val currentItem = medicamentos[position]
        holder.btnMedicamento.text = currentItem.produto


        holder.btnMedicamento.setOnClickListener {
            // Define a cor de fundo quando o botão é pressionado
            val colorFrom = Color.TRANSPARENT
            val colorTo = holder.itemView.context.resources.getColor(R.color.black) // Substitua pela sua cor de seleção
            val colorAnimation = ValueAnimator.ofObject(ArgbEvaluator(), colorFrom, colorTo)
            colorAnimation.duration = 1400 // Tempo da animação em milissegundos

            colorAnimation.addUpdateListener { animator ->
                holder.btnMedicamento.setBackgroundColor(animator.animatedValue as Int)
            }

            colorAnimation.start()

            // Adicione um atraso para a mudança de cor de volta para a cor padrão
            val handler = Handler(Looper.getMainLooper())
            handler.postDelayed({
                // Define a cor de fundo padrão quando o botão não está mais pressionado
                holder.btnMedicamento.setBackgroundResource(android.R.color.transparent)
            }, 1500) //
        }
    }


    override fun getItemCount() = medicamentos.size

    fun updateList(newList: List<Medicamento>) {
        medicamentos = newList
        notifyDataSetChanged()
    }
}

