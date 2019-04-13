package com.mvvm.koin.coroutines.sample.webservice

import com.mvvm.koin.coroutines.sample.data.room.Photo
import com.mvvm.koin.coroutines.sample.data.room.Place
import kotlinx.coroutines.Deferred
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface IApi {

    @POST("user/login")
    fun loginAsync(@Body request: LoginRequest): Deferred<LoginResponse>

    @POST("user/register")
    fun registerAsync(@Body request: RegisterRequest): Deferred<RegisterResponse>

    @GET("places")
    fun getPlacesAsync(): Deferred<List<Place>>

    @GET("photos")
    fun getPhotosAsync(): Deferred<List<Photo>>

}