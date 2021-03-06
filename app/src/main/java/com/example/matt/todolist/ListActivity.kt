package com.example.matt.todolist

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager

import kotlinx.android.synthetic.main.activity_list.*

class ListActivity : AppCompatActivity() {

    companion object {
        private const val REQUEST_CADASTRO: Int = 1 //para executar o cadastro de contatinho
        private const val LISTA_TAREFAS: String = "Lista de tarefas" //para salvar e restaurar a lista
    }

    private var tarefasList: MutableList<String> = mutableListOf()

    var indexTarefaClicada: Int = -1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        buttonAdd.setOnClickListener(){
            val cadastraTarefa = Intent(this, CadastraTarefaActivity::class.java)
            startActivityForResult(cadastraTarefa, REQUEST_CADASTRO)
        }
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == REQUEST_CADASTRO && resultCode == Activity.RESULT_OK){
            val novaTarefa: String? = data?.getStringExtra(CadastraTarefaActivity.TAREFA)
            if (novaTarefa != null) {
                if(indexTarefaClicada >= 0){
                    tarefasList.set(indexTarefaClicada, novaTarefa)
                    indexTarefaClicada = -1
                }else {
                    tarefasList.add(novaTarefa)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        carregaLista()
    }

    //salva a lista caso o Android venha a destruir a activity
    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

        outState?.putSerializable(LISTA_TAREFAS, tarefasList as ArrayList<String>)
    }

    //restaura a lista caso o Android venha a destruir a activity
    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)

        if(savedInstanceState != null)
            tarefasList = savedInstanceState.getSerializable(LISTA_TAREFAS) as MutableList<String>
    }

    fun carregaLista() {
        val adapter = ListaAdapter(tarefasList)

        adapter.setOnClickListener{tarefa, indexTarefaClicada ->
            this.indexTarefaClicada = indexTarefaClicada
            val editaTarefa = Intent(this, CadastraTarefaActivity::class.java)
            editaTarefa.putExtra(CadastraTarefaActivity.TAREFA, tarefa)
            this.startActivityForResult(editaTarefa, REQUEST_CADASTRO)
        }
        adapter.setOnDoneClickListener { indexTarefaClicada ->
            this.indexTarefaClicada = indexTarefaClicada
            tarefasList.removeAt(indexTarefaClicada)
            this.indexTarefaClicada = -1
            carregaLista()
        }
        val layoutManager = LinearLayoutManager(this)

        listaTarefas.adapter = adapter
        listaTarefas.layoutManager = layoutManager
    }
}
