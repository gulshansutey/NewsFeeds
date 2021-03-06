package com.gulshansutey.newsfeeds.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.gulshansutey.newsfeeds.R
import com.gulshansutey.newsfeeds.model.Fact


/**
 * RecyclerView Adapter to populate list in with given items.
 * We are using [ListAdapter] in for base class instead of [RecyclerView.Adapter] directly.
 * It computes the list data in background thread, and gives more control over the recycler view.
 * */

class FactsRecyclerAdapter : ListAdapter<Fact, RecyclerView.ViewHolder>(ITEM_COMPARATOR) {
    companion object {
        private val ITEM_COMPARATOR = object : DiffUtil.ItemCallback<Fact>() {
            override fun areItemsTheSame(oldItem: Fact, newItem: Fact): Boolean =
                oldItem.title == newItem.title

            override fun areContentsTheSame(oldItem: Fact, newItem: Fact): Boolean =
                oldItem == newItem
        }
    }

    /**
     * Called when RecyclerView needs a new {@link ViewHolder} of the given type to represent
     * an item.
     * The new ViewHolder will be used to display items of the adapter using #onBindViewHolder(ViewHolder, int)
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     *               an adapter position.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return FactsViewHolder.create(parent)
    }

    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the {@link ViewHolder#itemView} to reflect the item at the given
     * position.
     * @param holder The ViewHolder which should be updated to represent the contents of the
     *        item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            (holder as FactsViewHolder).bindData(item)
        }
    }


    /**
     * A ViewHolder describes an item view and metadata about its place within the RecyclerView.
     * All the individual views will hold a strong references to this view holder
     * @param view is the inflated new view hierarchy from the specified xml resource.
     * */

    class FactsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val title: AppCompatTextView = view.findViewById(R.id.tv_fact_title)
        private val description: AppCompatTextView = view.findViewById(R.id.tv_fact_description)
        private val image: AppCompatImageView = view.findViewById(R.id.iv_fact_image)

        companion object {

            /**
             * Inflate a new view hierarchy from the specified xml resource.
             *@param parent Optional view to be the parent of the generated hierarchy (if
             *        @param <em>attachToRoot</em> is true), or else simply an object that
             *        provides a set of LayoutParams values for root of the returned
             *        hierarchy (if <em>attachToRoot</em> is false.)
             *@return instance of [FactsViewHolder]
             */
            fun create(parent: ViewGroup): FactsViewHolder {
                //resource ID for an XML layout resource to load
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.adapter_fact_item, parent, false)
                return FactsViewHolder(view)
            }
        }

        /**
         * @param fact Object item of [Fact] type, binding data to views
         * */

        @SuppressLint("CheckResult")
        fun bindData(fact: Fact) {
            title.text = fact.title
            description.text = fact.description

            val requestOptions = RequestOptions()
            requestOptions.placeholder(R.drawable.ic_launcher_background)
            requestOptions.error(R.drawable.ic_launcher_background)

            Glide.with(itemView.context)
                .load(fact.imageUrl)
                .apply(requestOptions)
                .into(image)
        }


    }
}