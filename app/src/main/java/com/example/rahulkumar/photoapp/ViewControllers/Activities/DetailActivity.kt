package com.example.rahulkumar.photoapp.ViewControllers.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.AppCompatImageView
import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.Toolbar
import android.view.View
import com.example.rahulkumar.photoapp.Models.Util.Constants
import com.example.rahulkumar.photoapp.R
import com.example.rahulkumar.photoapp.ViewControllers.Interactors.IDetailView
import com.example.rahulkumar.photoapp.ViewControllers.Utils.Utility
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity(),IDetailView {

    val toolBar by lazy { findViewById<Toolbar>(R.id.toolBar) as Toolbar }

    val imageView by lazy { findViewById<AppCompatImageView>(R.id.imageView) as AppCompatImageView }

    val textDescription by lazy { findViewById<AppCompatTextView>(R.id.txtDescription) as AppCompatTextView }

    val name by lazy { findViewById<AppCompatTextView>(R.id.txtName) as AppCompatTextView }

    val txtLatitude by lazy { findViewById<AppCompatTextView>(R.id.txtLatitude) as AppCompatTextView }

    val txtLongitude by lazy { findViewById<AppCompatTextView>(R.id.txtLongitude) as AppCompatTextView }

    val txtFavCount by lazy { findViewById<AppCompatTextView>(R.id.txtfavCount) as AppCompatTextView }

    val btnDislike by lazy { findViewById<AppCompatImageView>(R.id.btnDislike) as AppCompatImageView }

    val btnLike by lazy { findViewById<AppCompatImageView>(R.id.btnLike) as AppCompatImageView }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        initViews()
    }

    override fun initViews() {
        toolBar.title = getString(R.string.photo_detail)
        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        toolBar.setNavigationOnClickListener {
            finish()
        }
        if(intent!=null){
            val bundle=intent.getBundleExtra(Constants.VALUE_BUNDLE)
            if(bundle!=null){
                Utility.loadImage(imageView,bundle.getString(Constants.IMAGE_URL))
                name.setText(bundle.getString(Constants.NAME))
                textDescription.setText(bundle.getString(Constants.DESC))
                txtLatitude.setText(bundle.getDouble(Constants.LATITUDE).toString())
                txtLongitude.setText(bundle.getDouble(Constants.LONGITUDE).toString())
                txtFavCount.setText(bundle.getInt(Constants.FAV_COUNT).toString())
            }
        }

        btnDislike.setOnClickListener {
            btnDislike.visibility= View.GONE
            btnLike.visibility=View.VISIBLE
        }

        btnLike.setOnClickListener {
            btnLike.visibility=View.GONE
            btnDislike.visibility=View.VISIBLE
        }

    }

}
