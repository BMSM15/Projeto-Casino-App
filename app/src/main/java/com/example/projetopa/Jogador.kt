package com.example.projetopa

import android.content.ContentValues

data class Livro(
    var titulo : String,
    var autor: String,
    var idCategoria: Long,
    var id: Long = -1
) {
    fun toContentValues() : ContentValues {
        val valores = ContentValues()

        valores.put(TabelaBDJogador.CAMPO_TITULO, titulo)
        valores.put(TabelaBDJogador.CAMPO_AUTOR, autor)
        valores.put(TabelaBDJogador.CAMPO_CATEGORIA_ID, idCategoria)

        return valores
    }
}