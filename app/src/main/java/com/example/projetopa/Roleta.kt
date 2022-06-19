package com.example.projetopa

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns

data class Roleta(
    var Ultimo_numero : Int,
    var Jogadas: Int,
    var id_roleta: Long = -1
) {
    fun toContentValues() : ContentValues {
        val valores = ContentValues()

        valores.put(TabelaBDRoleta.CAMPO_ULTIMO_NUMERO, Ultimo_numero)
        valores.put(TabelaBDRoleta.CAMPO_JOGADAS, Jogadas)

        return valores
    }

    companion object {
        fun fromCursor(cursor: Cursor): Roleta {
            val posId = cursor.getColumnIndex(BaseColumns._ID)
            val posUltimoNumero = cursor.getColumnIndex(TabelaBDRoleta.CAMPO_ULTIMO_NUMERO)
            val posJogadas = cursor.getColumnIndex(TabelaBDRoleta.CAMPO_JOGADAS)

            val id = cursor.getLong(posId)
            val Ultimo_numero = cursor.getInt(posUltimoNumero)
            val Jogadas = cursor.getInt(posUltimoNumero)

            return Roleta(Ultimo_numero,Jogadas, id)
        }
    }
}