package com.example.projetopa

import android.content.ContentValues

data class Jogador(
    var Nome_jogador : String,
    var dinheiro: Int,
    var id: Long = -1
) {
    fun toContentValues() : ContentValues {
        val valores = ContentValues()

        valores.put(TabelaBDJogador.CAMPO_NOME, Nome_jogador)
        valores.put(TabelaBDJogador.CAMPO_DINHEIRO, dinheiro)

        return valores
    }
}