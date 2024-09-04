package com.suhocki.githubusers.ui.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suhocki.githubusers.domain.user.UsersRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class UsersViewModel(
    private val usersRepository: UsersRepository,
) : ViewModel() {
    private val _error = MutableSharedFlow<Throwable?>()
    val error: Flow<Throwable?> = _error

    val users = usersRepository.users
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)

    init {
        viewModelScope.launch {
            if (usersRepository.users.first().isNotEmpty()) {
                loadNextPage()
            }
        }
    }

    fun loadNextPage() {
        viewModelScope.launch {
            delay(1000)
            runCatching {
                usersRepository.refreshNextPage()
            }.onFailure { error ->
                _error.emit(error)
            }
        }
    }
}