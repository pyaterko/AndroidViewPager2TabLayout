package com.example.viewpagerexample

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.owl_laugh_at_wasted_time.androidviewpager2tablayout.R

class PagerAdapter(private val context: Context, private val words: List<String>) :
    RecyclerView.Adapter<PagerAdapter.PageHolder>() {

    var onClickListenerSkip: (() -> Unit)? = null
    var onClickListenerOnwards: (() -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageHolder =
        PageHolder(LayoutInflater.from(context).inflate(R.layout.page_layout, parent, false))

    override fun onBindViewHolder(holder: PageHolder, position: Int) {
        holder.textView.text = words[position]
//        holder.skip.setOnClickListener {
//            onClickListenerSkip?.invoke()
//        }
//        holder.onwards.setOnClickListener {
//            onClickListenerOnwards?.invoke()
//        }

    }

    override fun getItemCount(): Int = words.size

    inner class PageHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView = view.findViewById<TextView>(R.id.textView)
//        val skip = view.findViewById<Button>(R.id.skip)
//        val onwards = view.findViewById<TextView>(R.id.onwards)
    }
}