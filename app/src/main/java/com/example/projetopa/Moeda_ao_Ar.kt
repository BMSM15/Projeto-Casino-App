package com.example.projetopa

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns

data class Moeda_ao_Ar(
    var Ultima_jogada : Int,
    var Acertou: Int,
    var Falhou: Int,
    var jogador: Jogador,
    var id_roleta: Long = -1
) {
    fun toContentValues() : ContentValues {
        val valores = ContentValues()

        valores.put(TabelaBDMoedaAoAR.CAMPO_ULTIMA_JOGADA, Ultima_jogada)
        valores.put(TabelaBDMoedaAoAR.CAMPO_ACERTOU, Acertou)
        valores.put(TabelaBDMoedaAoAR.CAMPO_FALHOU, Falhou)
        valores.put(TabelaBDMoedaAoAR.CAMPO_JOGADOR_ID, jogador.id)

        return valores
    }

    companion object {
        fun fromCursor(cursor: Cursor): Moeda_ao_Ar {
            val posId = cursor.getColumnIndex(BaseColumns._ID)
            val posUltimaJogada = cursor.getColumnIndex(TabelaBDMoedaAoAR.CAMPO_ULTIMA_JOGADA)
            val posAcertou = cursor.getColumnIndex(TabelaBDMoedaAoAR.CAMPO_ACERTOU)
            val posFalhou = cursor.getColumnIndex(TabelaBDMoedaAoAR.CAMPO_FALHOU)
            val posIdJoga = cursor.getColumnIndex(TabelaBDMoedaAoAR.CAMPO_JOGADOR_ID)
            val posNomeJoga = cursor.getColumnIndex(TabelaBDJogador.CAMPO_NOME)
            val posDinheiroJoga = cursor.getColumnIndex(TabelaBDJogador.CAMPO_DINHEIRO)

            val id = cursor.getLong(posId)
            val Ultima_jogada = cursor.getInt(posUltimaJogada)
            val Acertou = cursor.getInt(posAcertou)
            val Falhou = cursor.getInt(posFalhou)

            val idJogador = cursor.getLong(posIdJoga)
            val nomeJogador = cursor.getString(posNomeJoga)
            val DinheiroJoga = cursor.getString(posDinheiroJoga)
            val jogador = Jogador(nomeJogador,DinheiroJoga , idJogador)

            return Moeda_ao_Ar(Ultima_jogada, Acertou, Falhou, jogador, id)
        }
    }
}