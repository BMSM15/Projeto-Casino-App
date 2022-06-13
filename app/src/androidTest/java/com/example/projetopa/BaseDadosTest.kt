package com.example.projetopa

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns
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

        val jogador = Jogador("Bruno", 1500)
        insereJogador(db, jogador)

        db.close()
    }


}