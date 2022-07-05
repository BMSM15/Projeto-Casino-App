package com.example.projetopa

import android.database.Cursor
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projetopa.databinding.FragmentListaJogadoresBinding

class ListaJogadoresFragment : Fragment(), LoaderManager.LoaderCallbacks<Cursor> {
    var jogadorSeleccionado : Jogador? = null
        get() = field
        set(value) {
            field = value
            (requireActivity() as MainActivity).mostraOpcoesAlterarEliminar(field != null)
        }

    private var _binding: FragmentListaJogadoresBinding? = null
    private var adapterJogadores : AdapterJogadores? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentListaJogadoresBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        LoaderManager.getInstance(this).initLoader(ID_LOADER_JOGADORES, null, this)

        adapterJogadores = AdapterJogadores(this)
        binding.recyclerViewJogadores.adapter = adapterJogadores
        binding.recyclerViewJogadores.layoutManager = LinearLayoutManager(requireContext())

        val activity = activity as MainActivity
        activity.fragment = this
        activity.idMenuAtual = R.menu.menu_lista
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> =
        CursorLoader(
            requireContext(),
            ContentProviderJogadores.ENDERECO_JOGADORES,
            TabelaBDJogador.TODAS_COLUNAS,
            null,
            null,
            "${TabelaBDJogador.CAMPO_NOME}"
        )

    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
        adapterJogadores!!.cursor = data
    }

    override fun onLoaderReset(loader: Loader<Cursor>) {
        adapterJogadores!!.cursor = null
    }

    fun processaOpcaoMenu(item: MenuItem) : Boolean =
        when(item.itemId) {
            R.id.action_inserir -> {
                val acao = ListaJogadoresFragmentDirections.actionListaJogadoresFragmentToEditarjogaFragment()
                findNavController().navigate(acao)
                (activity as MainActivity).atualizaJogador(R.string.inserir_jogador_label)
                true
            }
            R.id.action_alterar -> {
                val acao = ListaJogadoresFragmentDirections.actionListaJogadoresFragmentToEditarjogaFragment(jogadorSeleccionado)
                findNavController().navigate(acao)
                (activity as MainActivity).atualizaJogador(R.string.alterar_jogador_label)
                true
            }
            R.id.action_eliminar -> {
                val acao = ListaJogadoresFragmentDirections.actionListaJogadoresFragmentToEliminarJogaFragment(jogadorSeleccionado!!)
                findNavController().navigate(acao)
                true
            }
            else -> false
        }

    companion object{
        const val ID_LOADER_JOGADORES = 0
    }
}