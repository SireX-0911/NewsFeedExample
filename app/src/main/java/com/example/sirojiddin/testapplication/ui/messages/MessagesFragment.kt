package com.example.sirojiddin.testapplication.ui.messages

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.View
import android.widget.Toast
import com.example.sirojiddin.testapplication.R
import com.example.sirojiddin.testapplication.common.BaseFragment
import com.example.sirojiddin.testapplication.ui.messages.adapter.MessagesAdapter
import kotlinx.android.synthetic.main.fragment_messages_layout.*
import com.example.sirojiddin.testapplication.utils.SwipeToDeleteCallback
import javax.inject.Inject


class MessagesFragment : BaseFragment(), MessagesContract.View {
    @Inject
    lateinit var presenter: MessagesContract.Presenter
    lateinit var messageAdapter: MessagesAdapter

    override fun getLayout(): Int = R.layout.fragment_messages_layout

    override fun init(savedInstanceState: Bundle?) {
        presenter.initMessages()
        messageAdapter = MessagesAdapter(context)
        val linearLayoutManager = LinearLayoutManager(context)
        rvMessages.layoutManager = linearLayoutManager
        rvMessages.adapter = messageAdapter
        val dividerItemDecoration = DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
        dividerItemDecoration.setDrawable(context?.let { ContextCompat.getDrawable(it, R.drawable.divider) }!!)
        rvMessages.addItemDecoration(dividerItemDecoration)

        val swipeHandler = object : SwipeToDeleteCallback(context!!){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder?, direction: Int) {
                presenter.removeItem(viewHolder?.adapterPosition)
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(rvMessages)
        val footerCallback = object :MessagesAdapter.FooterCallback{
            override fun onLoadMore() {
                presenter.loadNextItems()
            }

        }
        messageAdapter.setFooterCallback(footerCallback)
    }

    override fun setMessages(messages: ArrayList<Any>?) {
        messageAdapter.setItems(messages)
    }

    override fun updateMessages(messages: ArrayList<Any>?) {
        messageAdapter.removeFooter()
        messageAdapter.setItems(messages)
    }

    override fun connectionProblems() {
        Toast.makeText(activity, "Check internet connection", Toast.LENGTH_SHORT).show()
    }

    override fun showProgress() {
        pbLoading.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        pbLoading.visibility = View.GONE
    }
}