package com.example.projetopa

import android.content.ContentValues

data class Roleta(
    var Ultimo_numero : Int,
    var Jogadas: Int,
    var id_roleta: Long = -1
) {
    fun toContentValues() : ContentValues {
        val valores = ContentValues()

        valores.put(TabelaBDJogador.CAMPO_NOME, Ultimo_numero)
        valores.put(TabelaBDJogador.CAMPO_DINHEIRO, Jogadas)

        return valores
    }
}