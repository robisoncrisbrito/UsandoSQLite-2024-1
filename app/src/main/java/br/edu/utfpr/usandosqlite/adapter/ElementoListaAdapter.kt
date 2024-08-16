package br.edu.utfpr.usandosqlite.adapter

import android.content.Context
import android.database.Cursor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import br.edu.utfpr.usandosqlite.R
import br.edu.utfpr.usandosqlite.database.DatabaseHandler.Companion.ID
import br.edu.utfpr.usandosqlite.database.DatabaseHandler.Companion.NOME
import br.edu.utfpr.usandosqlite.database.DatabaseHandler.Companion.TELEFONE
import br.edu.utfpr.usandosqlite.entity.Cadastro

class ElementoListaAdapter (val context : Context, val cursor : Cursor) : BaseAdapter() {

    override fun getCount(): Int {
        return cursor.count
    }

    override fun getItem(position: Int): Any {
        cursor.moveToPosition( position )

        return Cadastro(
            cursor.getInt( ID ),
            cursor.getString( NOME ),
            cursor.getString( TELEFONE )
        )
    }

    override fun getItemId(position: Int): Long {
        cursor.moveToPosition( position )

        return cursor.getInt( ID ).toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater = context.getSystemService( Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val v = inflater.inflate( R.layout.elemento_lista, null )

        val tvNomeElementoLista = v.findViewById<TextView>( R.id.tvNomeElementoLista )
        val tvTelefoneElementoLista = v.findViewById<TextView>( R.id.tvTelefoneElementoLista )

        cursor.moveToPosition( position )

        tvNomeElementoLista.setText( cursor.getString( NOME ) )
        tvTelefoneElementoLista.setText( cursor.getString( TELEFONE ) )

        return v
    }
}