package com.example.sirojiddin.testapplication.ui.messages.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.sirojiddin.testapplication.R
import com.example.sirojiddin.testapplication.data.db.entity.Message
import com.example.sirojiddin.testapplication.ui.messages.model.Footer
import com.example.sirojiddin.testapplication.utils.FileTypeUtils
import kotlinx.android.synthetic.main.item_messages_adapter.view.*


class MessagesAdapter(val context: Context?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val footerView = 1
    private var footerCallback: FooterCallback? = null
    private var items = ArrayList<Any>()
    private var isFooterWorked = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        if (viewType == footerView) {
            return FooterViewHolder(inflater.inflate(R.layout.item_messages_adapter_footer, parent, false))
        }
        return MessageViewHolder(inflater.inflate(R.layout.item_messages_adapter, parent, false))
    }

    fun setItems(items: ArrayList<Any>?) {
        this.items = items!!
        items.add(Footer())
        isFooterWorked = false
        notifyDataSetChanged()
    }

    fun setFooterCallback(footerCallback: FooterCallback) {
        this.footerCallback = footerCallback
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is MessageViewHolder) {
            val message = items[position] as Message
            if (FileTypeUtils.isImage(message.text)) {
                val path = message.text
                context?.let {
                    Glide.with(it).load(path).into(holder.ivMessageImage)
                }!!
            } else {
                holder.tvMessageText.text = message.text
            }
        } else if (holder is FooterViewHolder) {
            if (!isFooterWorked) {
                footerCallback?.onLoadMore()
                isFooterWorked = true
            }
        }
    }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int {
        if (position == items.size) {
            return footerView
        }
        return super.getItemViewType(position)
    }

    fun removeFooter() {
        items.remove(Footer())
    }

    inner class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvMessageText: TextView = itemView.tvMessageText
        val ivMessageImage: ImageView = itemView.ivMessageImage
    }

    inner class FooterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    interface FooterCallback {
        fun onLoadMore()
    }
}