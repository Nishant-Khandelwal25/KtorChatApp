package com.example.ktorchatapp.presentation.username

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsernameViewModel @Inject constructor() : ViewModel() {
    private val _usernameText = mutableStateOf("")
    var usernameText: State<String> = _usernameText

    private val _onJoinChat = MutableSharedFlow<String>()
    val onJoinChat = _onJoinChat.asSharedFlow()

    fun onUsernameChanged(newUserName: String) {
        _usernameText.value = newUserName
    }

    fun onJoinClick() {
        viewModelScope.launch {
            if (_usernameText.value.isNotEmpty()) {
                _onJoinChat.emit(usernameText.value)
            }
        }
    }
}