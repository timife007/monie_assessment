package com.timife.moniepointassessment.presentation.shipment

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.timife.moniepointassessment.domain.Status
import com.timife.moniepointassessment.domain.shipments
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
class ShipmentViewModel @Inject constructor() : ViewModel() {

    private val _filterText = MutableStateFlow(Status.ALL)
    private val filterText = _filterText.asStateFlow()

    private val _searchList = MutableStateFlow(shipments)
    val searchList = filterText.combine(
        _searchList
    ) { status, items ->
        if (status == Status.ALL) {
            return@combine items
        }
        withContext(Dispatchers.Default){
            items.filter { item ->
                item.status == status
            }
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = _searchList.value
    )

    fun onSearchTextChange(filterItem: Status) {
        viewModelScope.launch {
            _filterText.value = filterItem
        }
        Log.d("list", searchList.value.toString())
    }
}