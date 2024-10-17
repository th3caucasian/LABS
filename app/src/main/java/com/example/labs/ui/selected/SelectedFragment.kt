package com.example.labs.ui.selected

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.labs.databinding.FragmentSelectedBinding

class SelectedFragment : Fragment() {

    private var _binding: FragmentSelectedBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val selectedViewModel =
            ViewModelProvider(this).get(SelectedViewModel::class.java)

        _binding = FragmentSelectedBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textSelected
        selectedViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}