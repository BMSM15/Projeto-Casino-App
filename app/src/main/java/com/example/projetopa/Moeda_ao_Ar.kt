package com.example.projetopa

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns

data class Moeda_ao_Ar(
    var Ultima_jogada : Int,
    var Acertou: Int,
    var Falhou: Int,
    var id_roleta: Long = -1
) {
    fun toContentValues() : ContentValues {
        val valores = ContentValues()

        valores.put(TabelaBDMoedaAoAR.CAMPO_ULTIMA_JOGADA, Ultima_jogada)
        valores.put(TabelaBDMoedaAoAR.CAMPO_ACERTOU, Acertou)
        valores.put(TabelaBDMoedaAoAR.CAMPO_FALHOU, Falhou)

        return valores
    }

    companion object {
        fun fromCursor(cursor: Cursor): Moeda_ao_Ar {
            val posId = cursor.getColumnIndex(BaseColumns._ID)
            val posUltimaJogada = cursor.getColumnIndex(TabelaBDMoedaAoAR.CAMPO_ULTIMA_JOGADA)
            val posAcertou = cursor.getColumnIndex(TabelaBDMoedaAoAR.CAMPO_ACERTOU)
            val posFalhou = cursor.getColumnIndex(TabelaBDMoedaAoAR.CAMPO_FALHOU)

            val id = cursor.getLong(posId)
            val Ultima_jogada = cursor.getInt(posUltimaJogada)
            val Acertou = cursor.getInt(posAcertou)
            val Falhou = cursor.getInt(posFalhou)

            return Moeda_ao_Ar(Ultima_jogada, Acertou, Falhou, id)
        }
    }
}