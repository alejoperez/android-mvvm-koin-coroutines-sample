package com.mvvm.koin.coroutines.sample.data.room

import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Test

@ExperimentalCoroutinesApi
class PlacesDaoTest : BaseDaoTest() {

    @Test
    fun placesTest() {
        dataBase.placeDao().savePlaces(getSampleData())

        val dataBasePlaces = dataBase.placeDao().getPlaces()

        assert(dataBasePlaces.isNotEmpty()) {
            "It should be places saved"
        }

        Assert.assertEquals("size should be ${getSampleData().size}",
                getSampleData().size,
                dataBasePlaces.size)
    }

    private fun getSampleData() = listOf(
            Place(1, "1", "1", 0.0, 0.0),
            Place(2, "2", "2", 0.0, 0.0),
            Place(3, "3", "3", 0.0, 0.0)
    )

}