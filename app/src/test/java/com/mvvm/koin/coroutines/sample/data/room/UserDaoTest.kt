package com.mvvm.koin.coroutines.sample.data.room

import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Test

@ExperimentalCoroutinesApi
class UserDaoTest: BaseDaoTest() {

    @Test
    fun userNotFoundTest() {
        val user = dataBase.userDao().getUser()
        Assert.assertNull("user should be null",user)
    }

    @Test
    fun userFoundTest() {
        dataBase.userDao().saveUser(getSampleData())

        val dataBaseUser = dataBase.userDao().getUser()

        Assert.assertEquals("userId should be ${getSampleData().id}",
                getSampleData().id,
                dataBaseUser.id)
    }

    private fun getSampleData() = User(1, "1", "1")

}