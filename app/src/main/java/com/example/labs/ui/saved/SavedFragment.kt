package com.example.labs.ui.saved

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
import com.example.labs.databinding.FragmentSavedBinding
import com.example.labs.ui.cities.RecycleViewAdapterCities

class SavedFragment : Fragment() {

    private var _binding: FragmentSavedBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val savedViewModel = ViewModelProvider(this).get(SavedViewModel::class.java)
        _binding = FragmentSavedBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val callerActivity: MainActivity2?
        callerActivity = requireActivity() as? MainActivity2
        if (callerActivity == null)
            Log.e("LOG", "Caller activity is not MainActivity2")
        else {
            val mRecycleView = binding.recycleViewSaved
            mRecycleView.setHasFixedSize(true)
            val mLayoutManager = LinearLayoutManager(callerActivity)
            mRecycleView.layoutManager = mLayoutManager
            val mAdapter = RecycleViewAdapterSaved(callerActivity.getSavedCities(), callerActivity)
            mRecycleView.adapter = mAdapter
            callerActivity.configureAdapterSaved(mAdapter)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}