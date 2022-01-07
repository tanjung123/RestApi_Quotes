package com.example.kuismws

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.rvitems_template.view.*

class MyAdapter  : RecyclerView.Adapter<MyAdapter.QuotesViewHolder>() {
    private val mData = ArrayList<Quotes>()
    private var onItemClickCallback: OnItemClickCallback? =null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun setData(items: ArrayList<Quotes>) {
        mData.clear()
        mData.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): MyAdapter.QuotesViewHolder {
        val mView = LayoutInflater.from(viewGroup.context).inflate(R.layout.rvitems_template, viewGroup, false)
        return QuotesViewHolder(mView)
    }

    override fun getItemCount(): Int = mData.size

    override fun onBindViewHolder(QuotesViewHolder: MyAdapter.QuotesViewHolder, position: Int) {
        QuotesViewHolder.bind(mData[position])
    }

    inner class QuotesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(quotes: Quotes) {
            with(itemView){
                tvAuthor.text = quotes.author
                tvText.text = quotes.text
                itemView.setOnClickListener { onItemClickCallback?.onItemClicked(quotes) }
            }
        }
    }
    interface OnItemClickCallback {
        fun onItemClicked(data: Quotes)
    }
}