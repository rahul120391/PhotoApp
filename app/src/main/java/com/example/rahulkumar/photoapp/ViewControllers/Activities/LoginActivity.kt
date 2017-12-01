package com.example.rahulkumar.photoapp.ViewControllers.Activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.AppCompatButton
import android.support.v7.widget.AppCompatEditText
import android.text.TextUtils
import android.view.View
import android.widget.FrameLayout
import com.example.rahulkumar.photoapp.Models.Interactor.ModelToViewDataTransfer
import com.example.rahulkumar.photoapp.Models.Network.WebServiceController
import com.example.rahulkumar.photoapp.R
import com.example.rahulkumar.photoapp.ViewControllers.Interactors.ILoginView
import com.example.rahulkumar.photoapp.ViewControllers.Utils.Utility
import com.fivehundredpx.api.auth.AccessToken

class LoginActivity : AppCompatActivity(), ILoginView, ModelToViewDataTransfer,View.OnClickListener {


    val webservicecontroller by lazy { WebServiceController(this) }

    val btnLogin by lazy { findViewById<AppCompatButton>(R.id.btnLogin) as AppCompatButton }

    val progressLayout by lazy { findViewById<FrameLayout>(R.id.progressLayout) as FrameLayout }

    val userEmail by lazy { findViewById<AppCompatEditText>(R.id.userEmail) as AppCompatEditText }

    val userPassword by lazy { findViewById<AppCompatEditText>(R.id.userPassword) as AppCompatEditText }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        btnLogin.setOnClickListener(this)
    }


    override fun showProgressBar() {
        progressLayout?.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        progressLayout?.visibility = View.GONE
    }


    override fun onClick(view: View) {
        when(view.id){
            R.id.btnLogin->{
                if (Utility.checkNetworkConnectivity()) {
                    if (!Utility.isValidEmail(userEmail?.text.toString().trim())) {
                        Utility.showToastWithMessage(getString(R.string.invalid_email))
                    } else if (TextUtils.isEmpty(userPassword?.text.toString().trim())) {
                        Utility.showToastWithMessage(getString(R.string.invalid_password))
                    } else {
                        showProgressBar()
                        webservicecontroller.login(userEmail?.text.toString().trim(), userPassword?.text.toString().trim())
                    }
                } else {
                    Utility.showToastWithMessage(getString(R.string.no_network))
                }
            }
        }
    }

    override fun Success(t: Any) {
        val token=t as AccessToken
        println("token is"+token.token)
        hideProgressBar()
        if (t is AccessToken) {
            finish()
            val intent = Intent(this, PhotosActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        } else {
           Utility.showToastWithMessage(getString(R.string.something_went_wrong))
        }

    }

    override fun Failure(message: String?) {
        hideProgressBar()
        if(message!=null){
            Utility.showToastWithMessage(message)
        }
        else{
            Utility.showToastWithMessage(getString(R.string.invalid_email_password))
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        if (webservicecontroller != null) {
            webservicecontroller.clearDisposables()
        }
    }
}
