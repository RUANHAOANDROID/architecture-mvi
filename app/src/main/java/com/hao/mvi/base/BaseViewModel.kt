package com.hao.mvi.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/**
 * @author 锅得铁
 * @param I a intent action
 * @param O a data
 */
abstract class BaseViewModel<I : IAction, O : IUiState> : ViewModel() {
    /**
     * Ui state launcher
     */
    private var _uiState = MutableSharedFlow<O>()

    /**
     *
     * Provide to view
     * @return LiveData<O>
     */
    fun uiState(): SharedFlow<O> = _uiState

    abstract fun doAction(action: I)

    fun send(o: O) {
        viewModelScope.launch {
            _uiState.emit(o)
        }
    }
}