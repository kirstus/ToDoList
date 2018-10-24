package com.example.matt.todolist

/**
 * Created by 619710 on 24/10/18.
 */
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import android.widget.Toast
import kotlinx.android.synthetic.main.item_lista.view.*

class ListaAdapter(val tarefas: List<String>)
    : RecyclerView.Adapter<ListaAdapter.ViewHolder>() {
    var clickListener:((tarefa:String, index: Int) -> Unit)? = null
    var btnDoneClickListener:((index: Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_lista, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return tarefas.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(tarefas[position], clickListener, btnDoneClickListener)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(tarefaNome: String, clickListener:((tarefa:String, index: Int) -> Unit)?, btnDoneClickListener:((index: Int) -> Unit)?) {
            itemView.tvNome.text = tarefaNome

            if(clickListener != null){
                itemView.setOnClickListener{
                    clickListener.invoke(tarefaNome, adapterPosition)
                }
            }
            if(btnDoneClickListener != null) {
                itemView.btnDelete.setOnClickListener {
                    btnDoneClickListener.invoke(adapterPosition)
                }
            }

        }
    }

    fun setOnClickListener(clique: ((tarefa:String, index: Int)-> Unit)){
        this.clickListener = clique;
    }
    fun setOnDoneClickListener(clique:((index:Int)-> Unit)){
        this.btnDoneClickListener = clique;
    }
}