package com.example.personaltraining.View.VAlimentacion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.personaltraining.databinding.FragmentGalimentacionBinding

class GalimentacionFragment : Fragment() {

    private var _binding: FragmentGalimentacionBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val galimentacionViewModel =
            ViewModelProvider(this).get(GalimentacionViewModel::class.java)

        _binding = FragmentGalimentacionBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textGalimentacion
        galimentacionViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}