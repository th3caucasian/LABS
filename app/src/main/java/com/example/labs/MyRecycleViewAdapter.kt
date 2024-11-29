package com.example.labs

import android.content.DialogInterface
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView

class MyRecycleViewAdapter(private val mDataset: Array<out String>?, private val activity: MainActivity2?): RecyclerView.Adapter<MyRecycleViewAdapter.MyViewHolder>() {

    class MyViewHolder(v: View): RecyclerView.ViewHolder(v) {
        val mTextView: TextView = v.findViewById(R.id.rec_text_view)

        fun bind(item: String?, activity: MainActivity2?) {
            mTextView.text = item
            itemView.setOnClickListener {
                activity?.onTextViewClicked(item)
            }
            itemView.setOnLongClickListener {
                val activity2Context = ContextProvider.getContext()
                val alertBuilder = AlertDialog.Builder(activity2Context)
                alertBuilder.setCancelable(true)
                    .setTitle("Сохранить информацию о городе?")
                    .setPositiveButton("Сохранить") { dialog, _ ->
                        activity?.onCityAdded(item)
                    }
                    .setNeutralButton("Отмена") { dialog, _ ->
                        activity?.onCityDeleted(item)
                    }
                val alertDialog = alertBuilder.create()
                alertDialog.show()
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.recycle_view_item, parent, false)
        val vh = MyViewHolder(v)
        return vh
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(mDataset!!.get(position), activity)
    }

    override fun getItemCount(): Int {
        return mDataset!!.size
    }
}