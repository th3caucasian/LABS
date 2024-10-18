package com.example.labs

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyRecycleViewAdapter(private val mDataset: Array<String>, private val activity: MainActivity2?): RecyclerView.Adapter<MyRecycleViewAdapter.MyViewHolder>() {

    class MyViewHolder(v: View): RecyclerView.ViewHolder(v) {
        val mTextView: TextView = v.findViewById(R.id.rec_text_view)

        fun bind(item: String, activity: MainActivity2?) {
            mTextView.text = item
            itemView.setOnClickListener {
                activity?.onTextViewClicked()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.recycle_view_item, parent, false)
        val vh = MyViewHolder(v)
        return vh
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(mDataset[position], activity)
        Log.e("TAG", "${mDataset[1]}, ${mDataset[3]}, ${mDataset[15]}")
    }

    override fun getItemCount(): Int {
        return mDataset.size
    }
}