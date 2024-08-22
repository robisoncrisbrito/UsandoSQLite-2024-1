package br.edu.utfpr.usandosqlite

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

class ListarActivity : AppCompatActivity() {

    private lateinit var lvRegistro : ListView

    private lateinit var banco : DatabaseHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listar)

        lvRegistro = findViewById( R.id.lvRegistros )
        banco = DatabaseHandler( this )

        val registros = banco.listCursor()

        val adapter = ElementoListaAdapter( this, registros )

        lvRegistro.adapter = adapter

    }


}