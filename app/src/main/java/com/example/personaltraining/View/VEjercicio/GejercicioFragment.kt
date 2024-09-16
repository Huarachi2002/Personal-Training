package com.example.personaltraining.View.VEjercicio

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.personaltraining.databinding.FragmentGrutinaBinding

class GejercicioFragment : Fragment() {

    private var _binding: FragmentGrutinaBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val grutinaViewModel =
            ViewModelProvider(this).get(GejercicioViewModel::class.java)

        _binding = FragmentGrutinaBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textGrutina
        grutinaViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}