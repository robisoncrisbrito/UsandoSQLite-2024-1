package br.edu.utfpr.usandosqlite.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper




class DatabaseHandler (context : Context) : SQLiteOpenHelper ( context, DATABASE_NAME, null, DATABASE_VERSION ) {

    override fun onCreate(db: SQLiteDatabase?) {
        banco.execSQL( "CREATE TABLE IF NOT EXISTS cadastro ( _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " nome TEXT, telefone TEXT )")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    companion object {
        private const val DATABASE_NAME = "dbfile.sqlite"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "cadastro"
        private const val ID = 0
        private const val NOME = 1
        private const val TELEFONE = 2
    }

}