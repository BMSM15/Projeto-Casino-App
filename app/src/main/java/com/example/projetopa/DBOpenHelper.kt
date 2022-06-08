package com.example.projetopa

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBOpenHelper(context : Context) : SQLiteOpenHelper(context, "user", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        requireNotNull(db)

        TabelaBDJogador(db).cria()
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }
    companion object {
        const val NOME = "Casino.db"
        private const val VERSAO = 1
    }
}