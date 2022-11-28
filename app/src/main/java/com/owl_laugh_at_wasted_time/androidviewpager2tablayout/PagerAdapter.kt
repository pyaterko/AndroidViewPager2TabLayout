package com.example.viewpagerexample

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.owl_laugh_at_wasted_time.androidviewpager2tablayout.R
import com.owl_laugh_at_wasted_time.androidviewpager2tablayout.databinding.PageLayoutBinding

class DiffCallBack(
    private val oldList: List<String>,
    private val newList: List<String>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition] == newList[newItemPosition]

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition] == newList[newItemPosition]

}

class PagerAdapter() :
    RecyclerView.Adapter<PagerAdapter.PageHolder>() {

     var words: List<String> = emptyList()
        set(value) {
            val result = DiffUtil.calculateDiff(DiffCallBack(field, value))
            field = value
            result.dispatchUpdatesTo(this)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PageLayoutBinding.inflate(inflater, parent, false)
        return PageHolder(binding)
    }

    override fun onBindViewHolder(holder: PageHolder, position: Int) {
        val text = words[position]
        holder.bind(text)
    }

    override fun getItemCount(): Int = words.size

    inner class PageHolder(
        //  val context: Context,
        val view: PageLayoutBinding
    ) : RecyclerView.ViewHolder(view.root) {
        fun bind(text: String) {
            //  val resources = view.root.resources
            val resId = R.drawable.btn_bg
            view.textView.text = text
            Glide.with(view.imageView)
                .load(resId)
                .transform(
                    CenterCrop(),
                    RoundedCorners(16)
                )
                .transition(DrawableTransitionOptions.withCrossFade())
                .transform().diskCacheStrategy(DiskCacheStrategy.NONE)
                .transition(DrawableTransitionOptions.withCrossFade())
             //   .transition(DrawableTransitionOptions().crossFade(3000))
                .into(view.imageView)
            //  view.imageView.setBackgroundColor(Color.GRAY)
        }
    }

}