package com.example.sirojiddin.testapplication.ui.messages

import com.example.sirojiddin.testapplication.common.BasePresenter
import com.example.sirojiddin.testapplication.common.BaseView
import com.example.sirojiddin.testapplication.data.db.entity.Message

interface MessagesContract {

    interface Presenter : BasePresenter {
        fun initMessages()
        fun loadNextItems()
        fun removeItem(position: Int?)
    }

    interface View : BaseView {
        fun connectionProblems()
        fun showProgress()
        fun hideProgress()
        fun setMessages(messages: ArrayList<Any>?)
    }
}