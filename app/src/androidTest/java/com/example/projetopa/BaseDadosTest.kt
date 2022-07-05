package com.example.projetopa

import android.database.sqlite.SQLiteDatabase
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class BaseDadosTest {
    private fun appContext() =
        InstrumentationRegistry.getInstrumentation().targetContext

    private fun getWritableDatabase(): SQLiteDatabase {
        val openHelper = DBOpenHelper(appContext())
        return openHelper.writableDatabase
    }

    private fun insereJogador(db: SQLiteDatabase, jogador: Jogador) {
        jogador.id = TabelaBDJogador(db).insert(jogador.toContentValues())
        assertNotEquals(-1, jogador.id)
    }

    @Before
    fun apagaBaseDados() {
        appContext().deleteDatabase(DBOpenHelper.NOME)
    }

    @Test
    fun consegueAbrirBaseDados() {
        val openHelper = DBOpenHelper(appContext())
        val db = openHelper.readableDatabase

        assertTrue(db.isOpen)

        db.close()
    }


    @Test
    fun consegueInserirJogador() {
        val db = getWritableDatabase()

        insereJogador(db, Jogador("Luís", "755"))

        db.close()
    }


    @Test
    fun consegueAlterarJogador() {
        val db = getWritableDatabase()

        val jogador = Jogador("Maria", "350")
        insereJogador(db, jogador)

        jogador.Nome_jogador = "Albertino"
        jogador.Dinheiro = "630"
        val registosAlterados = TabelaBDJogador(db).update(
            jogador.toContentValues(),
            "${TabelaBDJogador.CAMPO_ID}=?",
            arrayOf("${jogador.id}")
        )

        assertEquals(1, registosAlterados)

        db.close()
    }

    @Test
    fun consegueEliminarJogador() {
        val db = getWritableDatabase()

        val jogador = Jogador("Ana", "100")
        insereJogador(db, jogador)

        val registosEliminados = TabelaBDJogador(db).delete(
            "${TabelaBDJogador.CAMPO_ID}=?",
            arrayOf("${jogador.id}"))

        assertEquals(1, registosEliminados)

        db.close()
    }

    @Test
    fun consegueLerJogador() {
        val db = getWritableDatabase()

        val jogador = Jogador("Vanessa", "1500")
        insereJogador(db, jogador)

        val cursor = TabelaBDJogador(db).query(
            TabelaBDJogador.TODAS_COLUNAS,
            "${TabelaBDJogador.CAMPO_ID}=?",
            arrayOf("${jogador.id}"),
            null,
            null,
            null
        )

        assertEquals(1, cursor.count)
        assertTrue(cursor.moveToNext())

        val categoriaBD = Jogador.fromCursor(cursor)
        assertEquals(jogador, categoriaBD)

        db.close()
    }
}