package com.mvvm.koin.coroutines.sample.photos

import androidx.annotation.VisibleForTesting
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mvvm.koin.coroutines.sample.base.BaseViewModel
import com.mvvm.koin.coroutines.sample.data.room.Photo
import com.mvvm.koin.coroutines.sample.data.photos.PhotosRepository
import com.mvvm.koin.coroutines.sample.livedata.Event
import com.mvvm.koin.coroutines.sample.utils.getEventError
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PhotosViewModel(private val photosRepository: PhotosRepository): BaseViewModel() {

    val isLoading = ObservableBoolean(false)

    val photos = MutableLiveData<Event<List<Photo>>>()

    fun getPhotos() {

        viewModelScope.launch(contextProvider.getMainContext()) {

            try {
                showProgress()

                val response = withContext(contextProvider.getIoContext()) { photosRepository.getPhotosAsync().await() }

                photos.value = Event.success(response)

            } catch (t: Throwable) {
                photos.value = t.getEventError()
            } finally {
                hideProgress()
            }

        }
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun showProgress() = isLoading.set(true)

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun hideProgress() = isLoading.set(false)

}