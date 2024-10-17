package com.example.labs.ui.selected

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SelectedViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is selected Fragment"
    }
    val text: LiveData<String> = _text
}