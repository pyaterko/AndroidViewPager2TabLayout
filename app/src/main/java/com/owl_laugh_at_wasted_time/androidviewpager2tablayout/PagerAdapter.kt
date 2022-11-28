package com.example.viewpagerexample

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.owl_laugh_at_wasted_time.androidviewpager2tablayout.R
import com.owl_laugh_at_wasted_time.androidviewpager2tablayout.databinding.PageLayoutBinding

class PagerAdapter(private val words: List<String>) :
    RecyclerView.Adapter<PagerAdapter.PageHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageHolder{
        val inflater = LayoutInflater.from(parent.context)
        val binding = PageLayoutBinding.inflate(inflater, parent, false)
        return PageHolder(binding)
    }
    override fun onBindViewHolder(holder: PageHolder, position: Int) {
        val text=words[position]
        holder.bind(text)
    }

    override fun getItemCount(): Int = words.size

    inner class PageHolder( val view: PageLayoutBinding) : RecyclerView.ViewHolder(view.root){
       fun bind(text:String){
           view.textView.text = text
       }
    }

}