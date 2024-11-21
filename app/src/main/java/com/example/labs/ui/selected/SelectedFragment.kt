package com.example.labs.ui.selected

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.labs.MainActivity2
import com.example.labs.db.City
import com.example.labs.databinding.FragmentSelectedBinding
import com.example.labs.db.AppDatabase
import com.example.labs.db.CityDao
import com.example.labs.db.DatabaseProvider

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

        val textCity: TextView = binding.textSelected
        val textCountry: TextView = binding.textCountry
        val textLanguage: TextView = binding.textLanguage
        val textPopulation: TextView = binding.textPopulation
        val textSquare: TextView = binding.textSquare
        val city = arguments?.getSerializable("city") as City
        textCity.text = city.name
        textCountry.text = city.country
        textLanguage.text = city.language
        textPopulation.text = city.population.toString()
        textSquare.text = city.square.toString()


        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}