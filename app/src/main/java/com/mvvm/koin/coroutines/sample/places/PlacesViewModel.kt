package com.mvvm.koin.coroutines.sample.places

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mvvm.koin.coroutines.sample.base.BaseViewModel
import com.mvvm.koin.coroutines.sample.data.room.Place
import com.mvvm.koin.coroutines.sample.data.places.PlacesRepository
import com.mvvm.koin.coroutines.sample.livedata.Event
import com.mvvm.koin.coroutines.sample.utils.getEventError
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PlacesViewModel(private val placesRepository: PlacesRepository): BaseViewModel() {

    val places = MutableLiveData<Event<List<Place>>>()

    fun getPlaces() {
        viewModelScope.launch(contextProvider.getMainContext()) {
            try {
                val response = withContext(contextProvider.getIoContext()) { placesRepository.getPlacesAsync().await() }
                places.value = Event.success(response)
            } catch (t: Throwable) {
                places.value = t.getEventError()
            }
        }
    }

}