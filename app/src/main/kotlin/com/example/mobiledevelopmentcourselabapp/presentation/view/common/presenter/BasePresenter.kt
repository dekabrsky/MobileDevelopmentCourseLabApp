package com.example.mobiledevelopmentcourselabapp.presentation.view.common.presenter

import android.os.Bundle
import com.example.mobiledevelopmentcourselabapp.presentation.view.common.view.BaseMvpView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import moxy.MvpView

open class BasePresenter<V : BaseMvpView> : MvpPresenter<V>() {
    private val compositeDisposable = CompositeDisposable()

    private var loadingCounter = 0

    protected fun Disposable.disposeOnDestroy() {
        compositeDisposable.add(this)
    }

    protected fun <T : Any> Single<T>.withLoadingView(view: BaseMvpView) =
        this
            .doOnSubscribe {
                loadingCounter++
                if (loadingCounter == 1) view.setLoadingVisibility(true)
            }
            .doFinally {
                loadingCounter--
                if (loadingCounter == 0) view.setLoadingVisibility(false)
            }

    protected fun <T : Any> Single<T>.doAsync() =
        this.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    protected fun back() {
        viewState.back()
    }

    protected fun backWithResult(bundle: Bundle) {
        viewState.backWithResult(bundle)
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
        super.onDestroy()
    }
}