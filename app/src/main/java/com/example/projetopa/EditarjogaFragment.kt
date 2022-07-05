package com.example.projetopa

import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.SimpleCursorAdapter
import android.widget.Toast
import androidx.core.app.Person.fromBundle
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.example.projetopa.databinding.FragmentEditarJogaBinding

class EditarjogaFragment : Fragment() {
    private var _binding: FragmentEditarJogaBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var jogador: Jogador? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditarJogaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val activity = requireActivity() as MainActivity
        activity.fragment = this
        activity.idMenuAtual = R.menu.menu_edicao

        if (arguments != null) {
            jogador = EditarjogaFragmentArgs.fromBundle(requireArguments()).jogador

            if (jogador != null) {
                binding.editTextJogador.setText(jogador!!.Nome_jogador)
                binding.editTextDinheiro.setText(jogador!!.Dinheiro)
            }
        }
    }

    fun processaOpcaoMenu(item: MenuItem) : Boolean =
        when(item.itemId) {
            R.id.action_guardar -> {
                guardar()
                true
            }
            R.id.action_cancelar -> {
                voltaListaJogadores()
                true
            }
            else -> false
        }

    private fun guardar() {
        val jogador = binding.editTextJogador.text.toString()
        if (jogador.isBlank()) {
            binding.editTextJogador.error = getString(R.string.nome_jogador_obrigatorio)
            binding.editTextJogador.requestFocus()
            return
        }

        val dinheiro = binding.editTextDinheiro.text.toString()
        if (jogador.isBlank()) {
            binding.editTextDinheiro.error = getString(R.string.dinheiro_obrigatorio)
            binding.editTextDinheiro.requestFocus()
            return
        }

        val jogadorGuardado =
            if (jogador == null) {
                insereJogador(jogador, dinheiro)
            } else {
                alteraJogador(jogador, dinheiro)
            }

        if (jogadorGuardado) {
            Toast.makeText(requireContext(), R.string.jogador_inserido_sucesso, Toast.LENGTH_LONG)
                .show()
            voltaListaJogadores()
        } else {
            Snackbar.make(binding.editTextJogador, R.string.erro_inserir_jogador, Snackbar.LENGTH_INDEFINITE).show()
            return
        }
    }

    private fun alteraJogador(jogador: String, dinheiro: String) : Boolean {
        val jogador = Jogador(jogador, dinheiro)

        val enderecoJogador = Uri.withAppendedPath(ContentProviderJogadores.ENDERECO_JOGADORES, "${this.jogador!!.id}")

        val registosAlterados = requireActivity().contentResolver.update(enderecoJogador, jogador.toContentValues(), null, null)

        return registosAlterados == 1
    }

    private fun insereJogador(jogador: String, dinheiro: String): Boolean {
        val livro = Jogador(jogador, dinheiro)

        val enderecoLivroInserido = requireActivity().contentResolver.insert(ContentProviderJogadores.ENDERECO_JOGADORES, livro.toContentValues())

        return enderecoLivroInserido != null
    }

    private fun voltaListaJogadores() {
        findNavController().navigate(R.id.action_EditarjogaFragment_to_ListaJogadoresFragment)
    }
}