package com.example.sirojiddin.testapplication.data.network

import com.example.sirojiddin.testapplication.data.network.api.MessagesApi
import com.example.sirojiddin.testapplication.data.network.model.MessagesDTO
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import retrofit2.Response
import retrofit2.Retrofit

class ApiDatabaseImpl(retrofit: Retrofit) : ApiDatabase {

    private val messagesApi = retrofit.create(MessagesApi::class.java)

    override fun getMessages(page: Int, offset: Int?): Single<Response<MessagesDTO>> {
        return messagesApi.getMessages(
                page = page,
                offset = offset
        ).observeOn(AndroidSchedulers.mainThread())
    }
}