package com.ttdrp.gameofthrones.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ttdrp.gameofthrones.data.houses.HouseRepository
import com.ttdrp.gameofthrones.data.houses.HouseResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class HousesViewModel @Inject constructor(
    private val repository: HouseRepository,
) : ViewModel() {

    val pagingDataFlow: Flow<PagingData<HouseResponse>>

    init {
        val actionStateFlow = MutableSharedFlow<UiAction>()
        val loads = actionStateFlow
            .filterIsInstance<UiAction.Load>()
            .distinctUntilChanged()
            .onStart { emit(UiAction.Load) }

        pagingDataFlow = loads
            .flatMapLatest { loadHouse() }
            .cachedIn(viewModelScope)
    }

    private fun loadHouse(): Flow<PagingData<HouseResponse>> =
        repository.getHousesStream()

}

sealed class UiAction {
    object Load : UiAction()
}
