package com.example.matt.todolist

/**
 * Created by 619710 on 24/10/18.
 */
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import kotlinx.android.synthetic.main.item_lista.view.*

class ListaAdapter(val contatinhos: List<String>)
    : RecyclerView.Adapter<ListaAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_lista, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return contatinhos.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(contatinhos[position])
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(tarefaNome: String) {
            itemView.tvNome.text = tarefaNome
        }

    }
}