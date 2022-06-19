package com.example.projetopa

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class TabelaBDMoedaAoAR(db: SQLiteDatabase) : TabelaBD(db, NOME) {
    override fun cria() {
        db.execSQL("CREATE TABLE $nome (${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, $CAMPO_ULTIMA_JOGADA INTEGER NOT NULL, $CAMPO_ACERTOU INTEGER NOT NULL, $CAMPO_FALHOU INTEGER NOT NULL")
    }

    companion object {
        const val NOME = "Dados Moeda ao Ar"
        const val CAMPO_ULTIMA_JOGADA = "Ultima jogada"
        const val CAMPO_ACERTOU = "Acertou"
        const val CAMPO_FALHOU = "Falhou"
    }
}