package com.example.projetopa

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class TabelaBDRoleta(db: SQLiteDatabase) : TabelaBD(db, NOME) {
    override fun cria() {
        db.execSQL("CREATE TABLE $nome (${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, $CAMPO_ULTIMO_NUMERO INTEGER NOT NULL, $CAMPO_JOGADAS INTEGER NOT NULL")
    }

    companion object {
        const val NOME = "Dados Roleta"
        const val CAMPO_ID = "$NOME.${BaseColumns._ID}"
        const val CAMPO_ULTIMO_NUMERO = "Ultimo numero"
        const val CAMPO_JOGADAS = "Jogadas"

        val TODAS_COLUNAS = arrayOf(CAMPO_ID, CAMPO_ULTIMO_NUMERO, CAMPO_JOGADAS)
    }
}