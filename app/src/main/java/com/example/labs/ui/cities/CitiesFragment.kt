package com.example.labs.ui.cities

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.labs.MainActivity2
import com.example.labs.MyRecycleViewAdapter
import com.example.labs.R
import com.example.labs.databinding.FragmentCitiesBinding

class CitiesFragment : Fragment() {

    private var _binding: FragmentCitiesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val citiesViewModel = ViewModelProvider(this).get(CitiesViewModel::class.java)

        _binding = FragmentCitiesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textCities
        citiesViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        var callActivity: MainActivity2?
        callActivity = requireActivity() as? MainActivity2
        if (callActivity == null)
            Log.e("Cities Fragment", "This context is not MainActivity2")
        else {
            val mRecycleView = binding.recycleView
            mRecycleView.setHasFixedSize(true)
            val mLayoutManager = LinearLayoutManager(callActivity)
            mRecycleView.layoutManager = mLayoutManager
            val citiesList = resources.getStringArray(R.array.cities)
            val mAdapter = MyRecycleViewAdapter(citiesList, callActivity)
            mRecycleView.adapter = mAdapter
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}