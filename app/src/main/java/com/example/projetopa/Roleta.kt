package com.example.projetopa

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns

data class Roleta(
    var Ultimo_numero : Int,
    var Jogadas: Int,
    var jogador: Jogador,
    var id_roleta: Long = -1
) {
    fun toContentValues() : ContentValues {
        val valores = ContentValues()

        valores.put(TabelaBDRoleta.CAMPO_ULTIMO_NUMERO, Ultimo_numero)
        valores.put(TabelaBDRoleta.CAMPO_JOGADAS, Jogadas)
        valores.put(TabelaBDRoleta.CAMPO_JOGADOR_ID, jogador.id)

        return valores
    }

    companion object {
        fun fromCursor(cursor: Cursor): Roleta {
            val posId = cursor.getColumnIndex(BaseColumns._ID)
            val posUltimoNumero = cursor.getColumnIndex(TabelaBDRoleta.CAMPO_ULTIMO_NUMERO)
            val posJogadas = cursor.getColumnIndex(TabelaBDRoleta.CAMPO_JOGADAS)
            val posIdJoga = cursor.getColumnIndex(TabelaBDRoleta.CAMPO_JOGADOR_ID)
            val posNomeJoga = cursor.getColumnIndex(TabelaBDJogador.CAMPO_NOME)
            val posDinheiroJoga = cursor.getColumnIndex(TabelaBDJogador.CAMPO_DINHEIRO)

            val id = cursor.getLong(posId)
            val Ultimo_numero = cursor.getInt(posUltimoNumero)
            val Jogadas = cursor.getInt(posUltimoNumero)

            val idJogador = cursor.getLong(posIdJoga)
            val nomeJogador = cursor.getString(posNomeJoga)
            val DinheiroJoga = cursor.getInt(posDinheiroJoga)
            val jogador = Jogador(nomeJogador,DinheiroJoga , idJogador)

            return Roleta(Ultimo_numero, Jogadas, jogador, id)
        }
    }
}