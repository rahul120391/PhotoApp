package com.example.rahulkumar.photoapp.ViewControllers.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.ContentLoadingProgressBar
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.View
import com.example.rahulkumar.photoapp.Models.Interactor.ModelToViewDataTransfer
import com.example.rahulkumar.photoapp.Models.Model.MyPhotos
import com.example.rahulkumar.photoapp.Models.Model.Photo
import com.example.rahulkumar.photoapp.Models.Network.WebServiceController
import com.example.rahulkumar.photoapp.R
import com.example.rahulkumar.photoapp.ViewControllers.Adapters.MyPhotosAdapter
import com.example.rahulkumar.photoapp.ViewControllers.Interactors.IPhotoView
import com.example.rahulkumar.photoapp.ViewControllers.Utils.Utility



class PhotosActivity : AppCompatActivity(), IPhotoView, ModelToViewDataTransfer {


    val toolBar by lazy { findViewById<Toolbar>(R.id.toolBar) as Toolbar }

    val recyclerView by lazy { findViewById<RecyclerView>(R.id.recyclerView) as RecyclerView }

    val progressBar by lazy { findViewById<ContentLoadingProgressBar>(R.id.progressBar) as ContentLoadingProgressBar }

    val mainList by lazy { ArrayList<Photo>() }

    val adapter by lazy { MyPhotosAdapter(this) }

    val webservicecontroller by lazy { WebServiceController(this) }

    private var START: Int = 0
    private var END: Int = 4

    private var visibleThreshold: Int = 5
    private var lastVisibleItem: Int = 0
    private var firstVisiblePosition=0
    private var totalItemCount: Int = 0
    private var isLoading: Boolean = false
    lateinit var layoutManager: RecyclerView.LayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photos)
        initViews()
    }

    override fun initViews() {
        toolBar.title = getString(R.string.photos)
        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        toolBar.setNavigationOnClickListener {
            finish()
        }
        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
        fetchPhotos()
    }

    override fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }


    override fun Success(t: Any) {
        hideProgressBar()
        if (t != null) {
            val myphotos = t as MyPhotos
            val list=myphotos.photos
            println("size is"+list?.size)
            mainList.addAll(list as ArrayList<Photo>)
            if (mainList != null && mainList.size > 0) {
                if (mainList.size-1 > END) {
                    val list = mainList.subList(START, END).toList() as ArrayList<Photo>
                    adapter.setList(list,true)
                } else {
                    END = mainList.size - 1
                    val list = mainList.subList(START, END).toList() as ArrayList<Photo>
                    adapter.setList(list,false)
                }
            }
        }

    }

    override fun fetchPhotos() {
        if (Utility.checkNetworkConnectivity()) {
            showProgressBar()
            webservicecontroller.fetchPhotos()
        } else {
            Utility.showToastWithMessage(getString(R.string.no_network))
        }


        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView?.getLayoutManager() as LinearLayoutManager
                val pos = layoutManager.findLastCompletelyVisibleItemPosition()
                val numItems = recyclerView?.getAdapter().getItemCount()
                if(pos>=numItems-1 && END<mainList.size){

                    recyclerView?.postDelayed(object:Runnable{
                        override fun run() {
                           val list= mainList.subList(START,END).toList() as ArrayList<Photo>

                            START=END+1
                            END=START+4
                            var isLoading:Boolean=true
                            println("position is"+(pos-1))
                            println("end"+END)
                            if(END>=mainList.size-1){
                                isLoading=false
                            }
                            adapter.setList(list,isLoading)
                        }

                    },500)
                }
                else{

                }

            }
        })

    }

    override fun Failure(message: String?) {
        hideProgressBar()
        Utility.showToastWithMessage(message)
    }

    override fun onDestroy() {
        super.onDestroy()
        if(webservicecontroller!=null){
            webservicecontroller.clearDisposables()
        }
    }

}
