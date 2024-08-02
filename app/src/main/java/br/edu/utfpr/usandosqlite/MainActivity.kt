package br.edu.utfpr.usandosqlite

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.ContentView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

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

        banco.update( "cadastro", registro, "_id=" + etCod.text.toString(), null )

        Toast.makeText( this, "Sucesso!", Toast.LENGTH_LONG ).show()

    }
    fun btExcluirOnClick(view: View) {}
    fun btPesquisarOnClick(view: View) {}
    fun btListarOnClick(view: View) {
        val registro = banco.query( "cadastro", null, null, null, null, null, null )

        var saida = StringBuilder();

        while ( registro.moveToNext() ) {
            saida.append( registro.getInt( 0 ) )
            saida.append( " - " )
            saida.append( registro.getString( 1 ) )
            saida.append( " - " )
            saida.append( registro.getString( 2 ) )
            saida.append( "\n" )
        }

        Toast.makeText( this, saida.toString(), Toast.LENGTH_LONG ).show()
    }
}