package com.mvvm.koin.coroutines.sample.data.room

import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Test

@ExperimentalCoroutinesApi
class PhotosDaoTest : BaseDaoTest() {

    @Test
    fun photosTest() {
        dataBase.photoDao().savePhotos(getPhotosSampleData())

        val dataBasePhotos = dataBase.photoDao().getPhotos()

        assert(dataBasePhotos.isNotEmpty()) {
            "It should be photos saved"
        }

        Assert.assertEquals("size should be ${getPhotosSampleData().size}",
                getPhotosSampleData().size,
                dataBasePhotos.size)
    }

    private fun getPhotosSampleData(): List<Photo> = listOf(
            Photo(1, "1", "1", "1", "1"),
            Photo(2, "2", "2", "2", "2"),
            Photo(3, "3", "3", "3", "3")
    )

}