package com.example.projetopa

import android.database.Cursor
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class AdapterJogadores(val fragment: ListaJogadoresFragment) : RecyclerView.Adapter<AdapterJogadores.ViewHolderJogadores>() {
    var cursor: Cursor? = null
        get() = field
        set(value) {
            if (field != value) {
                field = value
                notifyDataSetChanged()
            }
        }

    var viewHolderSelecionado : ViewHolderJogadores? = null

    inner class ViewHolderJogadores(itemJogador: View) : RecyclerView.ViewHolder(itemJogador), View.OnClickListener{
        val textViewNome = itemJogador.findViewById<TextView>(R.id.textViewJogador)
        val textViewDinheiro = itemJogador.findViewById<TextView>(R.id.textViewDinheiro)

        init{
            itemJogador.setOnClickListener(this)
        }

        var jogador: Jogador? = null
        get() = field
        set(value: Jogador?) {
            field = value

            textViewNome.text = jogador?.Nome_jogador ?: ""
            textViewDinheiro.text = jogador?.Dinheiro ?: ""
        }

        override fun onClick(v: View?) {
            viewHolderSelecionado?.desseleciona()
            seleciona()
        }

        private fun seleciona() {
            itemView.setBackgroundResource(android.R.color.holo_orange_light)
            viewHolderSelecionado = this
            fragment.jogadorSeleccionado = jogador
        }

        private fun desseleciona() {
            itemView.setBackgroundResource(android.R.color.white)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderJogadores {
        val itemJogador = fragment.layoutInflater.inflate(R.layout.item_jogador, parent, false)
        return ViewHolderJogadores(itemJogador)
    }

    override fun onBindViewHolder(holder: ViewHolderJogadores, position: Int) {
        cursor!!.moveToPosition(position)
        holder.jogador = Jogador.fromCursor(cursor!!)
    }

    override fun getItemCount(): Int {
        if(cursor == null) return 0

        return cursor!!.count
    }
}