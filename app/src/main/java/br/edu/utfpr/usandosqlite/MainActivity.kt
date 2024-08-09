package br.edu.utfpr.usandosqlite

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.edu.utfpr.usandosqlite.database.DatabaseHandler
import br.edu.utfpr.usandosqlite.entity.Cadastro

class MainActivity : AppCompatActivity() {

    private lateinit var etCod : EditText
    private lateinit var etNome : EditText
    private lateinit var etTelefone : EditText

    private lateinit var banco : DatabaseHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etCod = findViewById( R.id.etCod )
        etNome = findViewById( R.id.etNome )
        etTelefone = findViewById( R.id.etTelefone )

        banco = DatabaseHandler( this )

    }

    fun btIncluirOnClick(view: View) {

        val cadastro = Cadastro(
            0,
            etNome.text.toString(),
            etTelefone.text.toString()
        )

        banco.insert( cadastro )

        Toast.makeText( this, "Sucesso!", Toast.LENGTH_LONG ).show()
    }
    
    
    fun btAlterarOnClick(view: View) {
        val cadastro = Cadastro(
            etCod.text.toString().toInt(),
            etNome.text.toString(),
            etTelefone.text.toString()
        )

        banco.update( cadastro )

        Toast.makeText( this, "Sucesso!", Toast.LENGTH_LONG ).show()

    }
    fun btExcluirOnClick(view: View) {
        banco.delete( etCod.text.toString().toInt() )

        Toast.makeText( this, "Sucesso!", Toast.LENGTH_LONG ).show()
    }

    fun btPesquisarOnClick(view: View) {

        val cadastro = banco.find( etCod.text.toString().toInt() )

        if ( cadastro != null ) {
            etNome.setText( cadastro.nome)
            etTelefone.setText( cadastro.telefone)
        } else {
            Toast.makeText(this, "Registro não encontrado", Toast.LENGTH_LONG).show()
        }

    }

    fun btListarOnClick(view: View) {
        val registros = banco.list()

        var saida = StringBuilder()

        registros.forEach {
            saida.append( it._id )
            saida.append( " - " )
            saida.append( it.nome )
            saida.append( " - " )
            saida.append( it.telefone )
            saida.append( "\n" )
        }

        Toast.makeText( this, saida.toString(), Toast.LENGTH_LONG ).show()
    }

    companion object {
        private const val ID = 0
        private const val NOME = 1
        private const val TELEFONE = 2
    }
}