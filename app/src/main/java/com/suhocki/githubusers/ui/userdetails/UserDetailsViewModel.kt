package com.suhocki.githubusers.ui.userdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suhocki.githubusers.domain.userdetails.UserDetailsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class UserDetailsViewModel(
    private val url: String,
    private val userDetailsRepository: UserDetailsRepository,
) : ViewModel() {
    private val _message = MutableSharedFlow<String?>()
    val message: Flow<String?> = _message

    val userDetails = userDetailsRepository.getUserDetails(url)

    init {
        viewModelScope.launch {
            runCatching {
                userDetailsRepository.refresh(url)
            }.onFailure { error ->
                _message.emit(error.localizedMessage)
            }
        }
    }
}