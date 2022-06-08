package com.example.projetopa

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class TabelaBDJogador(db: SQLiteDatabase) : TabelaBD(db, NOME) {
    override fun cria() {
        db.execSQL("CREATE TABLE $nome (${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, $CAMPO_DINHEIRO INTEGER NOT NULL")
    }

    companion object {
        const val NOME = "livros"
        const val CAMPO_DINHEIRO = "titulo"
    }
}