package com.example.labs

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.labs.ui.selected.SelectedFragment

class MyRecycleViewAdapter(private val mDataset: Array<String>, private val activity: AppCompatActivity): RecyclerView.Adapter<MyRecycleViewAdapter.MyViewHolder>() {

    class MyViewHolder(v: View): RecyclerView.ViewHolder(v) {
        val mTextView: TextView = v.findViewById(R.id.rec_text_view)

        fun bind(item: String, activity: AppCompatActivity) {
            mTextView.text = item
            itemView.setOnClickListener {
                val fragment = SelectedFragment()
                activity.supportFragmentManager.beginTransaction()
                    .replace(R.id.nav_host_fragment_activity_main2, fragment)
                    .addToBackStack(null)
                    .commit()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.recycle_view_item, parent, false)
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.mTextView.text = mDataset[position]
    }

    override fun getItemCount(): Int {
        return mDataset.size
    }
}