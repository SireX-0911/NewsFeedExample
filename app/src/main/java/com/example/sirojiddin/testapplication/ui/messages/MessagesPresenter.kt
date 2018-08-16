package com.example.sirojiddin.testapplication.ui.messages

import com.example.sirojiddin.testapplication.common.BasePresenterImpl
import com.example.sirojiddin.testapplication.data.DatabaseManager
import com.example.sirojiddin.testapplication.data.db.entity.File
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
            messages.addAll(file!!.messages as ArrayList)
            view?.setMessages(messages)
            view?.hideProgress()
        }
        messagesDisposable = apiDatabase.getMessages(page, 0).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.isSuccessful) {
                        if (it.body() != null) {
                            if (file != null) {
                                if (file!!.hashSum == it.body()!!.messages.hashCode()) {
                                    messages.addAll(it.body()!!.messages)
                                    view?.setMessages(messages)
                                } else {
                                    databaseManager.clearAllTables()
                                    val newFile = File(page, it.body()?.messages!!.hashCode(), it.body()?.messages!!)
                                    databaseManager.insertFile(newFile)
                                    for (i in it.body()!!.messages.indices) {
                                        databaseManager.insertMessages(it.body()!!.messages[i])
                                    }
                                    messages.addAll(it.body()!!.messages)
                                    view?.setMessages(messages)
                                }
                            } else {
                                val newFile = File(page, it.body()?.messages!!.hashCode(), it.body()?.messages!!)
                                databaseManager.insertFile(newFile)
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
                                val cacheMessages = file!!.messages as ArrayList
                                if (file!!.hashSum == it.body()!!.messages.hashCode()) {
                                    messages.addAll(cacheMessages)
                                    view?.updateMessages(messages)
                                } else {
                                    for (i in cacheMessages.indices) {
                                        databaseManager.deleteMessages(cacheMessages[i])
                                    }
                                    databaseManager.deleteFile(file!!)
                                    val newFile = File(page, it.body()?.messages!!.hashCode(), it.body()?.messages!!)
                                    databaseManager.insertFile(newFile)
                                    for (i in it.body()!!.messages.indices) {
                                        databaseManager.insertMessages(it.body()!!.messages[i])
                                    }
                                    messages.addAll(it.body()!!.messages)
                                    view?.updateMessages(messages)
                                }
                            } else {
                                val newFile = File(page, it.body()?.messages!!.hashCode(), it.body()?.messages!!)
                                databaseManager.insertFile(newFile)
                                for (i in it.body()!!.messages.indices) {
                                    databaseManager.insertMessages(it.body()!!.messages[i])
                                }
                                messages.addAll(it.body()!!.messages)
                                view?.updateMessages(messages)
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
        view?.updateMessages(messages)
    }

    override fun onDestroy() {
        messagesDisposable?.dispose()
    }

}