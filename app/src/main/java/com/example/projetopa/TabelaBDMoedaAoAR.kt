package com.example.projetopa

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteQueryBuilder
import android.provider.BaseColumns

class TabelaBDMoedaAoAR(db: SQLiteDatabase) : TabelaBD(db, NOME) {
    override fun cria() {
        db.execSQL("CREATE TABLE $nome (${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, $CAMPO_ULTIMA_JOGADA INTEGER NOT NULL, $CAMPO_ACERTOU INTEGER NOT NULL, $CAMPO_FALHOU INTEGER NOT NULL, $CAMPO_JOGADOR_ID INTEGER NOT NULL, FOREIGN KEY ($CAMPO_JOGADOR_ID) REFERENCES ${TabelaBDJogador.NOME}(${BaseColumns._ID}) ON DELETE RESTRICT)")
    }

    override fun query(
        columns: Array<String>,
        selection: String?,
        selectionArgs: Array<String>?,
        groupBy: String?,
        having: String?,
        orderBy: String?
    ): Cursor {
        val queryBuilder = SQLiteQueryBuilder()
        queryBuilder.tables = "$NOME INNER JOIN ${TabelaBDJogador.NOME} ON ${TabelaBDJogador.CAMPO_ID} = $CAMPO_JOGADOR_ID"

        return queryBuilder.query(db, columns, selection, selectionArgs, groupBy, having, orderBy)
    }

    companion object {
        const val NOME = "Dados_Moeda_ao_Ar"
        const val CAMPO_ID = "$NOME.${BaseColumns._ID}"
        const val CAMPO_ULTIMA_JOGADA = "Ultima_jogada"
        const val CAMPO_ACERTOU = "Acertou"
        const val CAMPO_FALHOU = "Falhou"
        const val CAMPO_JOGADOR_ID = "jogadorId"

        val TODAS_COLUNAS = arrayOf(CAMPO_ID, CAMPO_ULTIMA_JOGADA, CAMPO_ACERTOU, CAMPO_FALHOU, CAMPO_JOGADOR_ID, TabelaBDJogador.CAMPO_NOME)
    }
}