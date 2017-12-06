package com.example.rahulkumar.photoapp.Models.Network

import com.example.rahulkumar.photoapp.Models.Interactor.IAPIView
import com.example.rahulkumar.photoapp.Models.Interactor.ModelToViewDataTransfer
import com.example.rahulkumar.photoapp.Models.Model.MyPhotos
import com.example.rahulkumar.photoapp.Models.Util.Constants
import com.fivehundredpx.api.FiveHundredException
import com.fivehundredpx.api.auth.AccessToken
import com.fivehundredpx.api.tasks.XAuth500pxTask
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by rahulkumar on 01/12/17.
 */
class WebServiceController(modelToViewDataTransfer: ModelToViewDataTransfer) : IAPIView, XAuth500pxTask.Delegate {


    val modelToDataTransfer = modelToViewDataTransfer
    val compositeDisposable = CompositeDisposable()

    override fun login(email: String, password: String) {
        val loginExecute = XAuth500pxTask(this)
        loginExecute.execute(Constants.OAUTH_CONSUMER_KEY_VALUE, Constants.OAUTH_CONSUMER_SECRET_VALUE, email, password)
    }

    override fun fetchPhotos() {
        val photoflowable = RetrofitServiceProvider.serviceProvider?.fetchPhotos(Constants.OAUTH_CONSUMER_KEY_VALUE)?.cache()
        val disposable = photoflowable?.subscribeOn(Schedulers.io())
                ?.observeOn(Schedulers.io())
                ?.subscribe(
                        { myphotos: MyPhotos ->
                            moveResultFromIoToUi(myphotos)
                        }, { e: Throwable ->
                    moveErrorToUiThread(e)

                }, {

                }

                )
        compositeDisposable.add(disposable!!)
    }

    override fun onSuccess(result: AccessToken) {
        val disposable = Flowable.just(result)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { t: AccessToken? ->
                            modelToDataTransfer.Success(t as Any)
                        },
                        { e: Throwable ->
                            modelToDataTransfer.Failure(e.localizedMessage)
                        },
                        {

                        }

                )
        compositeDisposable.add(disposable)
    }

    override fun moveResultFromIoToUi(myPhotos: MyPhotos) {
        val disposable = Flowable.just(myPhotos).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread())
                .subscribe({ myphoto: MyPhotos ->
                    val any = myphoto as Any
                    modelToDataTransfer.Success(any)
                }
                        , { e: Throwable ->
                    modelToDataTransfer.Failure(e.localizedMessage)

                }, {

                })
        compositeDisposable.add(disposable)
    }


    override fun onFail(e: FiveHundredException) {
        val disposable = Flowable.just(e)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { error: FiveHundredException? ->
                            modelToDataTransfer.Failure(error?.localizedMessage)
                        },
                        { e: Throwable ->
                            modelToDataTransfer.Failure(e.localizedMessage)
                        },
                        {

                        }

                )
        compositeDisposable.add(disposable)
    }

    override fun moveErrorToUiThread(e: Throwable) {
        val disposable = Flowable.just(e)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { error: Throwable? ->
                            modelToDataTransfer.Failure(error?.localizedMessage)
                        },
                        { e: Throwable ->
                            modelToDataTransfer.Failure(e.localizedMessage)
                        },
                        {

                        }

                )
        compositeDisposable.add(disposable)
    }


    override fun clearDisposables() {
        compositeDisposable.dispose()
        compositeDisposable.clear()
    }


}