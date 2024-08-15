package br.edu.utfpr.usandosqlite

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ListarActivity : AppCompatActivity() {

    private lateinit var lvRegistro : ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listar)

        lvRegistro = findViewById( R.id.lvRegistros )

        val registros = listOf<String> ( "Brasil", "Argentina", "Paraguai", "Uruguai" )

        val adapter = ArrayAdapter<String>( this, android.R.layout.simple_list_item_1, registros )

        lvRegistro.adapter = adapter

    }
}