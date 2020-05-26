package com.sun_asterisk.moviedb_50.screen.details.adapter

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import com.sun_asterisk.moviedb_50.R
import com.sun_asterisk.moviedb_50.data.model.Cast
import com.sun_asterisk.moviedb_50.screen.base.BaseAdapter
import com.sun_asterisk.moviedb_50.screen.base.BaseViewHolder
import com.sun_asterisk.moviedb_50.utils.Constant
import com.sun_asterisk.moviedb_50.utils.GetImageAsyncTask
import com.sun_asterisk.moviedb_50.utils.OnFetchImageListener
import kotlinx.android.synthetic.main.item_cast.view.*

class CastAdapter : BaseAdapter<Cast, CastAdapter.ViewHolder>(), Filterable {

    var onItemClick: (Cast, Int) -> Unit = { _, _ -> }
    private var onNothingFound: (() -> Unit)? = null

    private val arrayList = ArrayList(items)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_cast, parent, false), onItemClick
        )

    class ViewHolder(
        itemView: View,
        onItemClick: (Cast, Int) -> Unit
    ) : BaseViewHolder<Cast>(itemView, onItemClick) {

        override fun onBindData(itemData: Cast) {
            super.onBindData(itemData)
            getImageCircle(itemData)
            itemView.castNameTextView.text = itemData.castName
        }

        private fun getImageCircle(cast: Cast?) {
            GetImageAsyncTask(
                object : OnFetchImageListener {

                    override fun onImageError(e: Exception?) {
                        e?.printStackTrace()
                    }

                    override fun onImageLoaded(bitmap: Bitmap?) {
                        bitmap?.let { itemView.castProfileImageView.setImageBitmap(bitmap) }
                    }
                }).execute(Constant.BASE_URL_IMAGE + cast?.castProfilePath)
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            private val filterResults = FilterResults()
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                items.clear()
                if (constraint == null || constraint.isEmpty()) {
                    items.addAll(arrayList)
                } else {
                    val searchResults =
                        arrayList.filter {
                            it.castName.toLowerCase()
                                .contains(constraint.toString().toLowerCase().trim())
                        }
                    items.addAll(searchResults)

                }
                return filterResults.also {
                    it.values = items
                }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                if (items.isNullOrEmpty())
                    onNothingFound?.invoke()
                notifyDataSetChanged()
            }
        }
    }

    fun search(s: String?, onNothingFound: (() -> Unit)?) {
        this.onNothingFound = onNothingFound
        filter.filter(s)
    }
}
