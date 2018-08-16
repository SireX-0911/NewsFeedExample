package com.example.sirojiddin.testapplication.data.network

import com.example.sirojiddin.testapplication.data.network.model.MessagesDTO
import io.reactivex.Single
import retrofit2.Response

interface ApiDatabase {
    fun getMessages(page: Int, offset: Int?) : Single<Response<MessagesDTO>>
}