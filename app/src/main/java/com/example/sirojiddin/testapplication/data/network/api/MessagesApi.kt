package com.example.sirojiddin.testapplication.data.network.api

import com.example.sirojiddin.testapplication.data.network.model.MessagesDTO
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MessagesApi {

    @GET("endpoint/{page}")
    fun getMessages(
            @Path("page") page : Int,
            @Query("offset") offset: Int? = null
    ) : Single<Response<MessagesDTO>>
}