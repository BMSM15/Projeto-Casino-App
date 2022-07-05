package com.example.projetopa

import android.content.DialogInterface
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.example.projetopa.databinding.FragmentEliminarJogaBinding

class EliminarJogaFragment : Fragment() {
    private var _binding: FragmentEliminarJogaBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var jogador: Jogador

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEliminarJogaBinding.inflate(inflater, container, false)
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
        activity.idMenuAtual = R.menu.menu_eliminar

        jogador = EliminarJogaFragmentArgs.fromBundle(requireArguments()).jogador

        binding.textViewJogador.text = jogador.Nome_jogador
        binding.textViewDinheiro.text = jogador.Dinheiro
    }

    fun processaOpcaoMenu(item: MenuItem) : Boolean =
        when(item.itemId) {
            R.id.action_eliminar -> {
                eliminaLivro()
                true
            }
            R.id.action_cancelar -> {
                voltaListaLivros()
                true
            }
            else -> false
        }

    private fun eliminaLivro() {
        val alertDialog = AlertDialog.Builder(requireContext())

        alertDialog.apply {
            setTitle(R.string.eliminar_jogador_label)
            setMessage(R.string.confirma_eliminar_jogador)
            setNegativeButton(android.R.string.cancel, DialogInterface.OnClickListener { dialogInterface, i ->  })
            setPositiveButton(R.string.eliminar, DialogInterface.OnClickListener { dialogInterface, i -> confirmaEliminarJogador() })
            show()
        }
    }

    private fun confirmaEliminarJogador() {
        val enderecoLivro = Uri.withAppendedPath(ContentProviderJogadores.ENDERECO_JOGADORES, "${jogador.id}")
        val registosEliminados = requireActivity().contentResolver.delete(enderecoLivro, null, null)

        if (registosEliminados != 1) {
            Snackbar.make(
                binding.textViewJogador,
                R.string.erro_eliminar_jogador,
                Snackbar.LENGTH_INDEFINITE
            ).show()
            return
        }

        Toast.makeText(requireContext(), R.string.jogador_eliminado_sucesso, Toast.LENGTH_LONG).show()
        voltaListaLivros()
    }

    private fun voltaListaLivros() {
        val acao = EliminarJogaFragmentDirections.actionEliminarJogaFragmentToListaJogadoresFragment()
        findNavController().navigate(acao)
    }
}