package br.edu.utfpr.usandosqlite

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var etCod : EditText
    private lateinit var etNome : EditText
    private lateinit var etTelefone : EditText

    private lateinit var banco : SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etCod = findViewById( R.id.etCod )
        etNome = findViewById( R.id.etNome )
        etTelefone = findViewById( R.id.etTelefone )

        banco = SQLiteDatabase.openOrCreateDatabase( this.getDatabasePath( "dbfile.sqlite" ), null )

        banco.execSQL( "CREATE TABLE IF NOT EXISTS cadastro ( _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " nome TEXT, telefone TEXT )")
        
    }

    fun btIncluirOnClick(view: View) {
        val registro = ContentValues()
        registro.put( "nome", etNome.text.toString() )
        registro.put( "telefone", etTelefone.text.toString() )

        banco.insert( "cadastro", null, registro )

        Toast.makeText( this, "Sucesso!", Toast.LENGTH_LONG ).show()
    }
    
    
    fun btAlterarOnClick(view: View) {
        val registro = ContentValues()
        registro.put( "nome", etNome.text.toString() )
        registro.put( "telefone", etTelefone.text.toString() )

        banco.update( "cadastro", registro, "_id=${etCod.text.toString()}", null )

        Toast.makeText( this, "Sucesso!", Toast.LENGTH_LONG ).show()

    }
    fun btExcluirOnClick(view: View) {
        banco.delete( "cadastro","_id=" + etCod.text.toString(), null )

        Toast.makeText( this, "Sucesso!", Toast.LENGTH_LONG ).show()
    }

    fun btPesquisarOnClick(view: View) {
        val registro = banco.query( "cadastro",
            null,
            "_id="+etCod.text.toString(),
            null,
            null,
            null,
            null
        )

        var saida = StringBuilder();

        if ( registro.moveToNext() ) {
            etNome.setText( registro.getString(Companion.NOME) )
            etTelefone.setText( registro.getString(Companion.TELEFONE) )
        } else {
            Toast.makeText(this, "Registro não encontrado", Toast.LENGTH_LONG).show()
        }

    }

    fun btListarOnClick(view: View) {
        val registro = banco.query( "cadastro",
            null,
            null,
            null,
            null,
            null,
            null
        )

        var saida = StringBuilder();

        while ( registro.moveToNext() ) {
            saida.append( registro.getInt(Companion.ID) )
            saida.append( " - " )
            saida.append( registro.getString(Companion.NOME) )
            saida.append( " - " )
            saida.append( registro.getString(Companion.TELEFONE) )
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