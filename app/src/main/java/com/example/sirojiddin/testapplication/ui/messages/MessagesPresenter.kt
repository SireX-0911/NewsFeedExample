package com.example.sirojiddin.testapplication.ui.messages

import com.example.sirojiddin.testapplication.common.BasePresenterImpl
import com.example.sirojiddin.testapplication.data.DatabaseManager
import com.example.sirojiddin.testapplication.data.db.entity.File
import com.example.sirojiddin.testapplication.data.db.entity.FileWithMessages
import com.example.sirojiddin.testapplication.data.db.entity.Message
import com.example.sirojiddin.testapplication.data.network.ApiDatabase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.net.UnknownHostException
import javax.inject.Inject

class MessagesPresenter @Inject constructor(view: MessagesContract.View?,
                                            private val apiDatabase: ApiDatabase,
                                            private val databaseManager: DatabaseManager
) : BasePresenterImpl<MessagesContract.View>(view), MessagesContract.Presenter {

    private var messagesDisposable: Disposable? = null
    var messages: ArrayList<Any> = ArrayList()
    var file: File? = null
    var page: Int = 0

    override fun initMessages() {
        messagesDisposable?.dispose()
        view?.showProgress()

        file = databaseManager.getFileById(page)
        if (file != null) {
            messages.addAll(databaseManager.getMessages(file!!.id).messages)
            view?.setMessages(messages)
            view?.hideProgress()
        }
        messagesDisposable = apiDatabase.getMessages(page, 0).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.isSuccessful) {
                        if (it.body() != null) {
                            if (file != null) {
                                if (file!!.hashCode == it.body()!!.messages.hashCode()) {
                                    messages.addAll(it.body()!!.messages)
                                    view?.setMessages(messages)
                                } else {
                                    databaseManager.clearAllTables()
                                    val newFile = File(page, it.body()?.messages!!.hashCode())
                                    databaseManager.insertFile(newFile)
                                    val fileWithMessage = FileWithMessages(page, it.body()?.messages!!)
                                    databaseManager.insertFileWithMessages(fileWithMessage)
                                    for (i in it.body()!!.messages.indices) {
                                        databaseManager.insertMessages(it.body()!!.messages[i])
                                    }
                                    messages.addAll(it.body()!!.messages)
                                    view?.setMessages(messages)
                                }
                            } else {
                                val newFile = File(page, it.body()?.messages!!.hashCode())
                                databaseManager.insertFile(newFile)
                                val fileWithMessage = FileWithMessages(page, it.body()?.messages!!)
                                databaseManager.insertFileWithMessages(fileWithMessage)
                                for (i in it.body()!!.messages.indices) {
                                    databaseManager.insertMessages(it.body()!!.messages[i])
                                }
                                messages.addAll(it.body()!!.messages)
                                view?.setMessages(messages)
                            }
                        }
                        view?.hideProgress()
                    } else {
                        view?.connectionProblems()
                    }
                }, {
                    if (it is UnknownHostException) {
                        view?.connectionProblems()
                    }
                    it.printStackTrace()
                })
    }

    override fun loadNextItems() {
        page++
        messagesDisposable = apiDatabase.getMessages(page, 0).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.isSuccessful) {
                        if (it.body() != null) {
                            file = databaseManager.getFileById(page)
                            if (file != null) {
                                val cacheMessages = databaseManager.getMessages(file!!.id).messages
                                if (file!!.hashCode == it.body()!!.messages.hashCode()) {
                                    messages.addAll(cacheMessages)
                                    view?.setMessages(messages)
                                } else {
                                    databaseManager.deleteFile(file!!)
                                    databaseManager.deleteFileWithMessages(databaseManager.getMessages(file!!.id))
                                    for (i in cacheMessages.indices) {
                                        databaseManager.deleteMessages(cacheMessages[i])
                                    }
                                    val newFile = File(page, it.body()?.messages!!.hashCode())
                                    databaseManager.insertFile(newFile)
                                    val fileWithMessage = FileWithMessages(page, it.body()?.messages!!)
                                    databaseManager.insertFileWithMessages(fileWithMessage)
                                    for (i in it.body()!!.messages.indices) {
                                        databaseManager.insertMessages(it.body()!!.messages[i])
                                    }
                                    messages.addAll(it.body()!!.messages)
                                    view?.setMessages(messages)
                                }
                            } else {
                                val newFile = File(page, it.body()?.messages!!.hashCode())
                                databaseManager.insertFile(newFile)
                                val fileWithMessage = FileWithMessages(page, it.body()?.messages!!)
                                databaseManager.insertFileWithMessages(fileWithMessage)
                                for (i in it.body()!!.messages.indices) {
                                    databaseManager.insertMessages(it.body()!!.messages[i])
                                }
                                messages.addAll(it.body()!!.messages)
                                view?.setMessages(messages)
                            }
                        }
                        view?.hideProgress()
                    } else {
                        view?.connectionProblems()
                    }
                }, {
                    if (it is UnknownHostException) {
                        view?.connectionProblems()
                    }
                    it.printStackTrace()
                })
    }

    override fun removeItem(position: Int?) {
        messages.removeAt(position!!)
        view?.setMessages(messages)
    }

    override fun onDestroy() {
        messagesDisposable?.dispose()
    }

}