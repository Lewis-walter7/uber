package com.licoding.uber.core.presentation

sealed interface MainUIEvent {
    data class OnSearchQueyChange(val searchQuery: String) : MainUIEvent
}