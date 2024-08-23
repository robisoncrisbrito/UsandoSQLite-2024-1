package br.edu.utfpr.usandosqlite

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.SimpleCursorAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.edu.utfpr.usandosqlite.adapter.ElementoListaAdapter
import br.edu.utfpr.usandosqlite.database.DatabaseHandler
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListarActivity : AppCompatActivity() {

    private lateinit var lvRegistro : ListView
    private lateinit var btIncluir : FloatingActionButton

    private lateinit var banco : DatabaseHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listar)

        lvRegistro = findViewById( R.id.lvRegistros )
        btIncluir = findViewById(R.id.btIncluir )

        btIncluir.setOnClickListener{
            val intent = Intent( this, MainActivity::class.java )
            startActivity( intent )
        }

        banco = DatabaseHandler( this )


    }

    override fun onStart() {
        super.onStart()
        val registros = banco.listCursor()
        val adapter = ElementoListaAdapter( this, registros )
        lvRegistro.adapter = adapter
    }

}