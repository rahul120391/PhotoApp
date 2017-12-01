package com.example.rahulkumar.photoapp.ViewControllers.Adapters

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.widget.ContentLoadingProgressBar
import android.support.v7.widget.AppCompatImageView
import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rahulkumar.mvvmarchitecture.View.SingletonContext
import com.example.rahulkumar.photoapp.Models.Model.Photo
import com.example.rahulkumar.photoapp.Models.Util.Constants
import com.example.rahulkumar.photoapp.R
import com.example.rahulkumar.photoapp.ViewControllers.Activities.DetailActivity
import com.example.rahulkumar.photoapp.ViewControllers.Utils.Utility
import kotlinx.android.synthetic.main.layout_footer_view.view.*
import kotlinx.android.synthetic.main.row_item.view.*

/**
 * Created by rahulkumar on 01/12/17.
 */
class MyPhotosAdapter(context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val photosList by lazy { ArrayList<Photo>() }
    val myContext = context

    val ITEM_VIEW = 0
    val FOOTER_VIEW = 1
    private var isLoading: Boolean = true

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is MyViewHolder) {
            if (photosList != null && photosList.size > 0 && position < photosList.size) {
                Utility.loadImage(holder.itemView.imageView, photosList.get(position).imageUrl)
                holder.itemView.txtName.setText(photosList.get(position).user?.fullname)
                holder.itemView.txtCity.setText(photosList.get(position).user?.city)
                holder.itemView.setOnClickListener {
                    val photo = photosList.get(position)
                    val bundle = Bundle()
                    if(photo.latitude!=null){
                        bundle.putDouble(Constants.LATITUDE, photo.latitude as Double)
                    }
                    else{
                        bundle.putDouble(Constants.LATITUDE, 0.0)
                    }
                    if(photo.longitude!=null){
                        bundle.putDouble(Constants.LONGITUDE, photo.longitude as Double)
                    }
                    else{
                        bundle.putDouble(Constants.LONGITUDE, 0.0)
                    }

                    bundle.putString(Constants.NAME, photo.user?.fullname)
                    bundle.putString(Constants.IMAGE_URL, photo.imageUrl)
                    bundle.putString(Constants.DESC, photo.description as String)
                    bundle.putInt(Constants.FAV_COUNT, photo.favoritesCount as Int)
                    val toDetail = Intent(myContext, DetailActivity::class.java)
                    toDetail.putExtra(Constants.VALUE_BUNDLE, bundle)
                    myContext.startActivity(toDetail)
                }
            }

        } else if (holder is MyFooterViewHolder) {
            if (isLoading) {
                holder.itemView.progressBar.visibility = View.VISIBLE
            } else {
                holder.itemView.progressBar.visibility = View.GONE
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == ITEM_VIEW) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.row_item, parent, false)
            return MyViewHolder(view)
        } else if (viewType == FOOTER_VIEW) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_footer_view, parent, false)
            return MyViewHolder(view)
        }
        return null!!
    }

    override fun getItemCount(): Int {
        if (isLoading) {
            if (photosList.size == 0) {
                return 0
            }
            return photosList.size + 1
        }
        return photosList.size
    }

    override fun getItemViewType(position: Int): Int {
        if (position != 0 && position == photosList.size) {
            return FOOTER_VIEW
        }
        return ITEM_VIEW
    }


    fun setList(list: ArrayList<Photo>, isLoading: Boolean) {
        this.isLoading = isLoading
        photosList.addAll(list)
        notifyDataSetChanged()
    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            val txtName by lazy { itemView.findViewById<AppCompatTextView>(R.id.txtName) as AppCompatTextView }
            val txtCity by lazy { itemView.findViewById<AppCompatTextView>(R.id.txtCity) as AppCompatTextView }
            val imageView by lazy { itemView.findViewById<AppCompatImageView>(R.id.imageView) as AppCompatImageView }
        }
    }

    class MyFooterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            val progressBar by lazy { itemView.findViewById<ContentLoadingProgressBar>(R.id.progressBar) as ContentLoadingProgressBar }
        }
    }
}