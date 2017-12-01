package com.example.rahulkumar.photoapp.ViewControllers.Utils

import android.content.Context
import android.net.ConnectivityManager
import android.text.TextUtils
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.rahulkumar.mvvmarchitecture.View.SingletonContext

/**
 * Created by rahulkumar on 01/12/17.
 */
object Utility {




    /** Network connectivity to check internet status **/
    fun checkNetworkConnectivity():Boolean{
        val connectivitymanager=SingletonContext.context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkinfo=connectivitymanager.activeNetworkInfo
        if(networkinfo != null &&
                networkinfo.isConnectedOrConnecting()){
            return true
        }
        return false
    }

    fun loadImage(view: ImageView, url: String?) {
        Glide.with(view.context).load(url).into(view)
    }


    fun isValidEmail(target: CharSequence): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }

    fun showToastWithMessage(message:String?){
        Toast.makeText(SingletonContext.context,message,Toast.LENGTH_SHORT).show()
    }

}