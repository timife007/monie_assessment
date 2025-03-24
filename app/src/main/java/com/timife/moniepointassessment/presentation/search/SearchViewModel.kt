package com.timife.moniepointassessment.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.timife.moniepointassessment.domain.searchItems
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(): ViewModel() {

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _searchList = MutableStateFlow(searchItems)
    val searchList = searchText.combine(
        _searchList
    ) { text, items ->
        if (text.isBlank()) {
            return@combine items
        }
        withContext(Dispatchers.Default){
            items.filter { item ->
                item.number.uppercase().contains(text.trim().uppercase())
            }
        }

    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = _searchList.value
    )


    fun onSearchTextChange(text: String) {
        viewModelScope.launch {
            _searchText.value = text
        }
    }

}

