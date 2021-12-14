package com.app.randomuser.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.randomuser.data.remote.dto.UserDto
import com.app.randomuser.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {

    var userList: List<UserDto> by mutableStateOf(listOf())
    var errorMessage: String by mutableStateOf("")
    var progress: Boolean by mutableStateOf(false)


    init {
        getRandomUsers()
    }

    private fun getRandomUsers() {
        progress = true
        viewModelScope.launch {
            try {
                val results = repository.getRandomUsers()
                userList = results.results
                progress = false
            } catch (e: Exception) {
                errorMessage = e.message.toString()
                progress = false
            }


        }
    }

}