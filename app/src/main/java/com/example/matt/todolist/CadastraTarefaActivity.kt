package com.example.matt.todolist

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_cadastra_tarefa.*

class CadastraTarefaActivity : AppCompatActivity() {

    companion object {
        const val TAREFA: String = "TAREFA"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastra_tarefa)

        val tarefa: String? = intent.getStringExtra(TAREFA)
        if(tarefa != null){
            edtNome.setText(tarefa)
        }

        btnSave.setOnClickListener(){
            salvarCadastro()
        }
    }

    private fun salvarCadastro() {

        val tarefaNome = edtNome.text.toString()

        val salvaCadastro = Intent(this, ListActivity::class.java)
        salvaCadastro.putExtra(TAREFA, tarefaNome)
        setResult(Activity.RESULT_OK, salvaCadastro)
        finish()
    }

}
